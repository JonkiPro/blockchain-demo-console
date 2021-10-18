package com.blockchain.core.database.repository;

import com.blockchain.core.database.model.Block;

import java.util.ArrayList;

/**
 * Block repository.
 *
 * @param <T> Objects inheriting from {@link com.blockchain.core.database.model.Block} class
 */
public interface BlockRepository<T extends Block> {

    /**
     * Add a new block to the database.
     *
     * @param block New block to add to database
     */
    void createBlock(final T block);

    /**
     * Get all blocks.
     *
     * @return List of all blocks
     */
    ArrayList<T> getBlocks();

    /**
     * Get the last block.
     *
     * @return The last block
     */
    T getLastBlock();

    /**
     * Check if any block exists in the database.
     *
     * @return True if any block exists
     */
    boolean existsBlocks();
}
