package com.blockchain.core.service;

import com.blockchain.core.dto.WalletDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interfaces for providing persistence functions for wallets.
 */
public interface WalletService {

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
    WalletDTO getWallet(final String address);

    /**
     * Get all wallets.
     *
     * @return List of all wallets
     */
    List<WalletDTO> getWallets();

    /**
     * Create a new transaction between the two wallets.
     *
     * @param addressFrom The sender`s wallet address.
     * @param addressTo The recipient`s wallet address.
     * @param amount The amount
     */
    void makeTransaction(final String addressFrom, final String addressTo, final BigDecimal amount);
}
