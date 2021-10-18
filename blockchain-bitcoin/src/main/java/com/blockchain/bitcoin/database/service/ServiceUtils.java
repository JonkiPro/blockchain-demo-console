package com.blockchain.bitcoin.database.service;

import com.blockchain.bitcoin.dto.BlockBitcoinDTO;
import com.blockchain.bitcoin.dto.TransactionBitcoinDTO;
import com.blockchain.bitcoin.database.model.TransactionBitcoin;
import com.blockchain.core.database.model.Block;
import com.blockchain.core.database.model.Wallet;
import com.blockchain.core.dto.WalletDTO;

/**
 * Utility methods for services.
 */
public final class ServiceUtils {

    /**
     * Convert a block model to a DTO for external exposure.
     *
     * @param block The model to convert
     * @return The immutable DTO representation of the model data
     */
    public static BlockBitcoinDTO toBlockDTO(final Block block) {
        return new BlockBitcoinDTO.Builder(
                block.getId(),
                block.getHash(),
                block.getData(),
                block.getTimestamp(),
                block.getNonce(),
                block.getTransactions())
                .builder();

    }

    /**
     * Convert a transaction model to a DTO for external exposure.
     *
     * @param transaction The model to convert
     * @return The immutable DTO representation of the model data
     */
    public static TransactionBitcoinDTO toTransactionBitcoinDTO(final TransactionBitcoin transaction) {
        return new TransactionBitcoinDTO.Builder(
                transaction.getHash(),
                transaction.getHashBlock(),
                transaction.getAddressSender(),
                transaction.getAddressRecipient(),
                transaction.getData(),
                transaction.getTimestamp(),
                transaction.getAmount())
                .withFees(transaction.getFees())
                .builder();
    }

    /**
     * Convert a wallet model to a DTO for external exposure.
     *
     * @param wallet The model to convert
     * @return The immutable DTO representation of the model data
     */
    public static WalletDTO toWalletDTO(final Wallet wallet) {
        return new WalletDTO.Builder(
                wallet.id(),
                wallet.address(),
                wallet.code(),
                wallet.amount(),
                wallet.transactionsSent(),
                wallet.transactionsReceived())
                .builder();
    }
}
