package com.blockchain.core.dto;

import com.blockchain.core.database.model.Transaction;
import com.blockchain.core.util.TextColor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Read only data transfer object representing a Block.
 */
public class BlockDTO {
    private final long id;
    private final String hash;
    private final String data;
    private final long timestamp;
    private final long nonce;

    private final ArrayList<? extends Transaction> transactions;

    /**
     * Constructor.
     *
     * @param builder Internal class builder object
     */
    protected BlockDTO(final Builder builder) {
        this.id = builder.bId;
        this.hash = builder.bHash;
        this.data = builder.bData;
        this.timestamp = builder.bTimestamp;
        this.nonce = builder.bNonce;
        this.transactions = builder.bTransactions;
    }

    /**
     * Get the block ID.
     *
     * @return The block ID
     */
    public long getId() {
        return this.id;
    }

    /**
     * Get the block hash.
     *
     * @return The block hash
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * Get the block data.
     *
     * @return The block data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Get the block timestamp.
     *
     * @return The block timestamp
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * Get the block nonce.
     *
     * @return The block nonce
     */
    public long getNonce() {
        return this.nonce;
    }

    /**
     * Get the block transactions.
     *
     * @return The block transactions
     */
    public ArrayList<? extends Transaction> getTransactions() {
        return this.transactions;
    }

    /**
     * A builder to create block.
     */
    protected static class Builder {

        private final long bId;
        private final String bHash;
        private final String bData;
        private final long bTimestamp;
        private final long bNonce;
        private final ArrayList<? extends Transaction> bTransactions;

        /**
         * Constructor which has required fields.
         *
         * @param id The block ID
         * @param hash The block hash
         * @param data Data of the block
         * @param timestamp The block timestamp
         * @param nonce The block nonce
         * @param transactions The block transactions
         */
        protected Builder(
                final long id,
                final String hash,
                final String data,
                final long timestamp,
                final long nonce,
                final ArrayList<? extends Transaction> transactions
        ) {
            this.bId = id;
            this.bHash = hash;
            this.bData = data;
            this.bTimestamp = timestamp;
            this.bNonce = nonce;
            this.bTransactions = transactions;
        }

        /**
         * Get the new block.
         *
         * @return The new block
         */
        public BlockDTO builder() {
            return new BlockDTO(this);
        }
    }

    /**
     * Override the toString() method.
     *
     * @return Textual representation of an object
     */
    @Override
    public String toString() {
        return "Date: " + TextColor.YELLOW + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(this.getTimestamp()) + TextColor.RESET
                + " Id: " + TextColor.YELLOW + this.getId() + TextColor.RESET
                + " Hash: " + TextColor.YELLOW + this.getHash() + TextColor.RESET
                + " Data: " + TextColor.YELLOW + this.getData() + TextColor.RESET
                + " Number of transactions: " + TextColor.YELLOW + this.getTransactions().size() + TextColor.RESET
                + " Nonce: " + TextColor.YELLOW + this.getNonce() + TextColor.RESET;
    }
}
