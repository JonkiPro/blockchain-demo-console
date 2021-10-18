package com.blockchain.core.database.model;

import com.blockchain.core.util.HashModelUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Representation of the block.
 */
public abstract class Block extends HashModel {
    private long id;
    private final String previousHash;
    private final String data;
    private final long timestamp;
    private final long nonce;

    private ArrayList<? extends Transaction> transactions;

    /**
     * Constructor to create a new block before writing to the database.
     *
     * @param previousHash Hash of the previous block
     * @param data Data of the block
     * @param nonce A random integer (long)
     */
    protected Block(
            final String previousHash,
            final String data,
            final long nonce
    ) {
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.nonce = nonce;
        this.setHash(HashModelUtils.getHash(this));
    }

    /**
     * Constructor to load a block from the database.
     *
     * @param id The block ID
     * @param hash The block hash
     * @param previousHash Hash of the previous block
     * @param data Data of the block
     * @param timestamp The block timestamp
     * @param nonce The block nonce
     * @param transactions The block transactions
     */
    protected Block(
            final long id,
            final String hash,
            final String previousHash,
            final String data,
            final long timestamp,
            final long nonce,
            final ArrayList<? extends Transaction> transactions
    ) {
        this.id = id;
        this.setHash(hash);
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.transactions = transactions;
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
     * Get the block previous hash.
     *
     * @return The block previous hash
     */
    public String getPreviousHash() {
        return this.previousHash;
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
}
