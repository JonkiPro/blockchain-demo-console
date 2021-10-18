package com.blockchain.bitcoin.database.model;

import com.blockchain.core.database.model.Transaction;

import java.math.BigDecimal;

/**
 * Representation of the transaction bitcoin.
 */
public class TransactionBitcoin extends Transaction {
    private final BigDecimal fees;

    /**
     * Constructor to create a new transaction before writing to the database.
     *
     * @param hashBlock The hash of the block in which this transaction is placed
     * @param addressSender The transaction sender`s address
     * @param addressRecipient The transaction recipient`s address
     * @param data The transaction data
     * @param amount The transaction amount
     * @param fees The transaction fees
     */
    public TransactionBitcoin(
            final String hashBlock,
            final String addressSender,
            final String addressRecipient,
            final String data,
            final BigDecimal amount,
            final BigDecimal fees
    ) {
        super(hashBlock, addressSender, addressRecipient, data, amount);
        this.fees = fees;
    }

    /**
     * Constructor to load a transaction from the database.
     *
     * @param hash The transaction hash
     * @param hashBlock The hash of the block in which this transaction is placed
     * @param addressSender The transaction sender`s address
     * @param addressRecipient The transaction recipient`s address
     * @param data The transaction data
     * @param timestamp The transaction timestamp
     * @param amount The transaction amount
     * @param fees The transaction fees
     */
    public TransactionBitcoin(
            final String hash,
            final String hashBlock,
            final String addressSender,
            final String addressRecipient,
            final String data,
            final long timestamp,
            final BigDecimal amount,
            final BigDecimal fees
    ) {
        super(hash, hashBlock, addressSender, addressRecipient, data, timestamp, amount);
        this.fees = fees;
    }

    /**
     * Get the transaction fees.
     *
     * @return The transaction fees
     */
    public BigDecimal getFees() {
        return this.fees;
    }
}
