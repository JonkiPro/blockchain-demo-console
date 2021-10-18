package com.blockchain.bitcoin.controller;

import com.blockchain.bitcoin.database.DbInitialization;
import com.blockchain.bitcoin.dto.BlockBitcoinDTO;
import com.blockchain.bitcoin.dto.TransactionBitcoinDTO;
import com.blockchain.bitcoin.dto.request.TransactionBitcoinRequest;
import com.blockchain.bitcoin.database.service.BlockServiceImpl;
import com.blockchain.bitcoin.database.service.TransactionServiceImpl;
import com.blockchain.bitcoin.database.service.WalletServiceImpl;
import com.blockchain.core.dto.WalletDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Bitcoin's main blockchain runtime class.
 */
public final class BlockchainBitcoinController implements Runnable {
    private static BlockchainBitcoinController instance;

    private final BlockServiceImpl blockService;
    private final TransactionServiceImpl transactionService;
    private final WalletServiceImpl walletService;

    private final BigDecimal FEES = new BigDecimal("0.0005");

    /**
     * Constructor.
     */
    private BlockchainBitcoinController() {
        this.blockService = new BlockServiceImpl();
        this.transactionService = new TransactionServiceImpl();
        this.walletService = new WalletServiceImpl();

        new DbInitialization().initTables();
        new DbInitialization().createRandomWallets();
    }

    /**
     * Get the only instance of the class.
     *
     * @return Instance of the class
     */
    public static BlockchainBitcoinController getInstance() {
        if(instance == null) {
            instance = new BlockchainBitcoinController();
        }
        return instance;
    }

    /**
     * Mine(create) new blocks.
     */
    public void mineBlock() {
        this.blockService.createBlock();
    }

    /**
     * Get all blocks.
     *
     * @return List of all blocks
     */
    public List<BlockBitcoinDTO> getBlocks() {
        return this.blockService.getBlocks();
    }

    /**
     * Add a new transaction to the database.
     *
     * @param transactionRequest New transaction to add to database
     * @return The transaction created
     */
    public TransactionBitcoinDTO addTransaction(final TransactionBitcoinRequest transactionRequest) {
        return this.transactionService.createTransaction(transactionRequest);
    }
    /**
     * Get all transactions.
     *
     * @return List of all transactions
     */
    public List<TransactionBitcoinDTO> getTransactions() {
        return this.transactionService.getTransactions();
    }

    /**
     * Add a new wallet to the database.
     *
     * @param address New wallet address
     * @return True if the wallet has been created
     */
    public boolean createWallet(String address) {
        return this.walletService.createWallet(address);
    }

    /**
     * Get the user's wallet.
     *
     * @return The user's wallet
     */
    public WalletDTO getWallet(final String hash) {
        return this.walletService.getWallet(hash);
    }

    /**
     * Get all wallets.
     *
     * @return List of all wallets
     */
    public List<WalletDTO> getWallets() {
        return this.walletService.getWallets();
    }

    /**
     * Get current fees for transactions on the bitcoin network.
     *
     * @return The fees
     */
    public BigDecimal getFees() {
        return this.FEES;
    }

    /**
     * Overriding the run() method.
     */
    @Override
    public void run() {
        for(int i = 0; i <= 21000000; i++) {
            this.mineBlock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
