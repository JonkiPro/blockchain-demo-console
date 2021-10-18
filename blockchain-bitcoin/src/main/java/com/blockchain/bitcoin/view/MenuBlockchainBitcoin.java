package com.blockchain.bitcoin.view;

import com.blockchain.bitcoin.dto.TransactionBitcoinDTO;
import com.blockchain.bitcoin.dto.request.TransactionBitcoinRequest;
import com.blockchain.bitcoin.controller.BlockchainBitcoinController;
import com.blockchain.bitcoin.util.KeyUtils;
import com.blockchain.bitcoin.util.LocaleUserUnits;
import com.blockchain.core.util.SeedUtils;
import com.blockchain.core.dto.WalletDTO;
import com.blockchain.core.exception.ResourceException;
import com.blockchain.core.util.TextColor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Class that displays a menu for the bitcoin blockchain.
 */
public class MenuBlockchainBitcoin {

    /**
     * Show the main menu of the bitcoin blockchain.
     */
    public static void showMenu() {
        initLocalUser();
        boolean loop = true;
        while(loop) {
            final Scanner sc = new Scanner(System.in);
            System.out.println("\r\n\r\nWelcome in " + TextColor.YELLOW + "Blockchain Bitcoin!" + TextColor.RESET);
            System.out.println("Your address is: " + TextColor.YELLOW + LocaleUserUnits.LOCALE_USER_ADDRESS + TextColor.RESET);
            System.out.println("Amount wallet: " + TextColor.YELLOW + BlockchainBitcoinController.getInstance().getWallet(LocaleUserUnits.LOCALE_USER_ADDRESS).getAmount().doubleValue() + " BTC" + TextColor.RESET);
            System.out.println("1. Show Blocks Bitcoin (Every 1 second, 1 block is extracted)");
            System.out.println("2. Add transaction");
            System.out.println("3. Show all transactions");
            System.out.println(TextColor.RED + "0. Back" + TextColor.RESET);
            System.out.print("Choice: ");
            final String choice = sc.nextLine();

            switch (choice) {
                case "1" -> showList(BlockchainBitcoinController.getInstance().getBlocks());
                case "2" -> showMenuAddTransaction();
                case "3" -> showList(BlockchainBitcoinController.getInstance().getTransactions());
                case "0" -> loop = false;
                default -> System.out.println(TextColor.RED + "WRONG CHOICE!" + TextColor.RESET);
            }
        }
        System.out.println("\r\n\r\n");
    }

    /**
     * Display the list.
     *
     * @param list The list
     */
    public static void showList(final List<?> list) {
        list.forEach(object -> System.out.println(object.toString()));
        System.out.println("\r\n Click enter to return to menu...");
        try {
            System.in.read();
        } catch (final IOException ignored) {}
    }

    /**
     * Display the menu for adding transactions.
     */
    public static void showMenuAddTransaction() {
        final Scanner sc = new Scanner(System.in);

        System.out.println("\r\nSelect an address from the available list!");
        final List<WalletDTO> wallets = BlockchainBitcoinController.getInstance().getWallets();
        wallets.forEach(System.out::println);
        System.out.print("Choice: ");
        int choiceId;
        String recipientAddress;
        try {
            choiceId = Integer.parseInt(sc.nextLine());
            if(choiceId < 0
                    || choiceId >= wallets.size()) {
                System.out.println("\r\n" + TextColor.RED + "WRONG CHOICE!" + TextColor.RESET);
                return;
            }
            recipientAddress = wallets.get(choiceId).getAddress();
        } catch(final NumberFormatException e) {
            System.out.println("\r\n" + TextColor.RED + "WRONG CHOICE!" + TextColor.RESET);
            return;
        }
        System.out.println("\r\nEnter the data you want to send!");
        System.out.println("1. Text data");
        System.out.println("2. BTC");
        System.out.print("Choice: ");
        final String choice = sc.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("\r\n\r\nData: ");
                sc.nextLine();
                final String data = sc.nextLine();

                try {
                    final TransactionBitcoinDTO transaction = BlockchainBitcoinController.getInstance().addTransaction(
                            new TransactionBitcoinRequest(
                                    LocaleUserUnits.LOCALE_USER_ADDRESS,
                                    recipientAddress,
                                    data,
                                    new BigDecimal(0)));

                    System.out.println("\r\n\r\nSuccess!");
                    System.out.println(transaction);
                } catch (final ResourceException e) {
                    System.out.println(e.getMessage());
                }
            }
            case "2" -> {
                System.out.print("\r\n\r\nBTC: ");
                double btc;

                try {
                    btc = sc.nextDouble();
                } catch(final InputMismatchException e) {
                    System.out.println("\r\n" + TextColor.RED + "SPECIFY THE APPROPRIATE VALUE!" + TextColor.RESET);
                    return;
                }

                try {
                    final TransactionBitcoinDTO transaction = BlockchainBitcoinController.getInstance().addTransaction(
                            new TransactionBitcoinRequest(
                                    LocaleUserUnits.LOCALE_USER_ADDRESS,
                                    recipientAddress,
                                    "",
                                    new BigDecimal(btc)));

                    System.out.println("\r\n\r\nSuccess!");
                    System.out.println(transaction);
                } catch (final ResourceException e) {
                    System.out.println(e.getMessage());
                }
            }
            default -> System.out.println(TextColor.RED + "WRONG CHOICE!" + TextColor.RESET);
        }
    }

    /**
     * Initialize the local user.
     */
    public static void initLocalUser() {
        if(LocaleUserUnits.LOCALE_USER_ADDRESS == null) {
            if (!LocaleUserUnits.existsFile()) {
                final ArrayList<String> generateSeed = SeedUtils.generate();
                System.out.println("\r\nHi. You're new. We have generated your seed: " + TextColor.YELLOW + generateSeed + TextColor.RESET);
                final String privateKey = KeyUtils.generatePrivateKey(generateSeed);
                System.out.println("Private key: " + TextColor.YELLOW + privateKey + TextColor.RESET);
                final String publicKey = KeyUtils.encryptKey(privateKey);
                System.out.println("Public key: " + TextColor.YELLOW + publicKey + TextColor.RESET + "\r\n");
                LocaleUserUnits.LOCALE_USER_ADDRESS = publicKey;
                LocaleUserUnits.saveFileWithSeedAndKeys(generateSeed, privateKey, publicKey);
                BlockchainBitcoinController.getInstance().createWallet(publicKey);
            } else {
                System.out.println("\r\nPublic key: " + TextColor.YELLOW + LocaleUserUnits.geyPublicKey() + TextColor.RESET + "\r\n");
            }
        } else {
            System.out.println("\r\nPublic key: " + TextColor.YELLOW + LocaleUserUnits.LOCALE_USER_ADDRESS + TextColor.RESET + "\r\n");
        }
    }
}
