package com.blockchain.core.database.repository;

import com.blockchain.core.database.model.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Wallet repository.
 */
public interface WalletRepository {

    /**
     * Add a new wallet to the database.
     *
     * @param address New wallet address
     * @return True if the wallet has been created
     */
    boolean createWallet(final String address);

    /**
     * Get the wallet by address.
     *
     * @param address The wallet address
     * @return The wallet
     */
    Wallet getWallet(final String address);

    /**
     * Get all wallets.
     *
     * @return List of all created wallets
     */
    ArrayList<Wallet> getWallets();

    /**
     * Update the wallet amount.
     *
     * @param id The wallet ID
     * @param newValue New amount for the wallet
     * @return True if the wallet amount has been updated
     */
    boolean updateWallet(final long id, final BigDecimal newValue);
}
