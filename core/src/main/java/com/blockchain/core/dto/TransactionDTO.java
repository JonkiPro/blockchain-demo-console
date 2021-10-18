package com.blockchain.core.dto;

import com.blockchain.core.util.TextColor;

import java.math.BigDecimal;

/**
 * Read only data transfer object representing a Transaction.
 */
public class TransactionDTO {
    private final String hash;
    private final String hashBlock;
    private final String addressSender;
    private final String addressRecipient;
    private final String data;
    private final long timestamp;
    private final BigDecimal amount;

    /**
     * Constructor.
     *
     * @param builder Internal class builder object
     */
    protected TransactionDTO(final Builder builder) {
        this.hash = builder.bHash;
        this.hashBlock = builder.bHashBlock;
        this.addressSender = builder.bAddressSender;
        this.addressRecipient = builder.bAddressRecipient;
        this.data = builder.bData;
        this.timestamp = builder.bTimestamp;
        this.amount = builder.bAmount;
    }

    /**
     * Get the transaction hash.
     *
     * @return The transaction hash.
     */
    public String getHash() {
        return this.hash;
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
     * Get the transaction sender`s address.
     *
     * @return The transaction sender`s address
     */
    public String getAddressSender() {
        return this.addressSender;
    }

    /**
     * Get the transaction recipient`s address.
     *
     * @return The transaction recipient`s address
     */
    public String getAddressRecipient() {
        return this.addressRecipient;
    }

    /**
     * Get the transaction data.
     *
     * @return The transaction data.
     */
    public String getData() {
        return this.data;
    }

    /**
     * Get the transaction timestamp.
     *
     * @return The transaction timestamp.
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * Get the transaction amount.
     *
     * @return The transaction amount.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * A builder to create transactions.
     */
    protected static class Builder {

        private final String bHash;
        private final String bHashBlock;
        private final String bAddressSender;
        private final String bAddressRecipient;
        private final String bData;
        private final long bTimestamp;
        private final BigDecimal bAmount;

        /**
         * Constructor which has required fields.
         *
         * @param hash The transaction hash
         * @param hashBlock The hash of the block in which this transaction is placed
         * @param addressSender The transaction sender`s address
         * @param addressRecipient The transaction recipient`s address
         * @param data The transaction data
         * @param timestamp The transaction timestamp
         * @param amount The transaction amount
         */
        protected Builder(
                final String hash,
                final String hashBlock,
                final String addressSender,
                final String addressRecipient,
                final String data,
                final long timestamp,
                final BigDecimal amount
        ) {
            this.bHash = hash;
            this.bHashBlock = hashBlock;
            this.bAddressSender = addressSender;
            this.bAddressRecipient = addressRecipient;
            this.bData = data;
            this.bTimestamp = timestamp;
            this.bAmount = amount;
        }

        /**
         * Get the new transaction.
         *
         * @return The new transaction
         */
        public TransactionDTO builder() {
            return new TransactionDTO(this);
        }
    }

    /**
     * Override the toString() method.
     *
     * @return Textual representation of an object
     */
    @Override
    public String toString() {
        return "\r\nTransaction " + TextColor.YELLOW + this.getHash() + TextColor.RESET
                + "\r\n from address "
                + TextColor.YELLOW + this.getAddressSender() + TextColor.RESET
                + " to address "
                + TextColor.YELLOW + this.getAddressRecipient() + TextColor.RESET
                + " data containing "
                + TextColor.YELLOW + (this.getData().isBlank() ? "null" : this.getData()) + TextColor.RESET
                + " BTC with a value of "
                + TextColor.YELLOW + this.getAmount().toString() + TextColor.RESET
                + " in block "
                + TextColor.YELLOW + this.getHashBlock() + TextColor.RESET;
    }
}
