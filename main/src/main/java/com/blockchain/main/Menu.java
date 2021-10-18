package com.blockchain.main;;

import com.blockchain.bitcoin.view.MenuBlockchainBitcoin;
import com.blockchain.core.util.TextColor;

import java.util.Scanner;

/**
 * A class that displays a blockchain selection menu.
 */
public class Menu {

    /**
     * A method that displays a blockchain selection menu.
     */
    public static void show() {

        while(true) {
            final Scanner sc = new Scanner(System.in);
            System.out.println("Welcome in " + TextColor.YELLOW + " Blockchain Explorer!" + TextColor.RESET);
            System.out.println("1. Blockchain Bitcoin");
            System.out.print("Choice: ");
            final String choice = sc.nextLine();

            switch (choice) {
                case "1" -> MenuBlockchainBitcoin.showMenu();
                default -> System.out.println(TextColor.RED + "WRONG CHOICE!" + TextColor.RESET);
            }
        }
    }
}
