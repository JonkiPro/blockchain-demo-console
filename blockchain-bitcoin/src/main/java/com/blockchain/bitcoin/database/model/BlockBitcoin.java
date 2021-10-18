package com.blockchain.bitcoin.database.model;

import com.blockchain.core.database.model.Block;

import java.util.ArrayList;

/**
 * Representation of the block bitcoin.
 */
public class BlockBitcoin extends Block {

    /**
     * Constructor to create a new block before writing to the database.
     *
     * @param previousHash The block previous hash
     * @param data The block data
     * @param nonce The block nonce
     */
    public BlockBitcoin(
            final String previousHash,
            final String data,
            final long nonce
    ) {
        super(previousHash, data, nonce);
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
    public BlockBitcoin(
            final long id,
            final String hash,
            final String previousHash,
            final String data,
            final long timestamp,
            final long nonce,
            final ArrayList<TransactionBitcoin> transactions
    ) {
        super(id, hash, previousHash, data, timestamp, nonce, transactions);
    }
}
