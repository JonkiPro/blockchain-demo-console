package com.blockchain.core.database.model;

import com.blockchain.core.util.HashModelUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Representation of the transaction.
 */
public abstract class Transaction extends HashModel {
    private long id;
    private final String hashBlock;
    private final String addressSender;
    private final String addressRecipient;
    private final String data;
    private final long timestamp;
    private final BigDecimal amount;

    /**
     * Constructor to create a new transaction before writing to the database.
     *
     * @param hashBlock The hash of the block in which this transaction is placed
     * @param addressSender The transaction sender`s address
     * @param addressRecipient The transaction recipient`s address
     * @param data The transaction data
     * @param amount The transaction amount
     */
    protected Transaction(
            final String hashBlock,
            final String addressSender,
            final String addressRecipient,
            final String data,
            final BigDecimal amount
    ) {
        this.hashBlock = hashBlock;
        this.addressSender = addressSender;
        this.addressRecipient = addressRecipient;
        this.data = data;
        this.amount = amount;
        this.timestamp = new Date().getTime();
        this.setHash(HashModelUtils.getHash(this));
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
     */
    protected Transaction(
            final String hash,
            final String hashBlock,
            final String addressSender,
            final String addressRecipient,
            final String data,
            final long timestamp,
            final BigDecimal amount
    ) {
        this.setHash(hash);
        this.hashBlock = hashBlock;
        this.addressSender = addressSender;
        this.addressRecipient = addressRecipient;
        this.data = data;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    /**
     * Get the transaction ID.
     *
     * @return The transaction ID.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Get the hash of the block in which this transaction is placed.
     *
     * @return The hash of the block in which this transaction is placed
     */
    public String getHashBlock() {
        return this.hashBlock;
    }

    /**
     * Get the sender`s address.
     *
     * @return The transaction sender`s address
     */
    public String getAddressSender() {
        return this.addressSender;
    }

    /**
     * Get the recipient`s address.
     *
     * @return The transaction recipient`s address
     */
    public String getAddressRecipient() {
        return this.addressRecipient;
    }

    /**
     * Get the transaction data.
     *
     * @return The transaction data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Get the transaction timestamp.
     *
     * @return The transaction timestamp
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * Get the transaction amount.
     *
     * @return The transaction amount
     */
    public BigDecimal getAmount() {
        return this.amount;
    }
}
