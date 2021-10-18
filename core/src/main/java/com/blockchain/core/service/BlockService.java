package com.blockchain.core.service;

import com.blockchain.core.dto.BlockDTO;

import java.util.List;

/**
 * Interfaces for providing persistence functions for blocks.
 *
 * @param <T> Objects inheriting from {@link com.blockchain.core.dto.BlockDTO} class
 */
public interface BlockService<T extends BlockDTO> {

    /**
     * Add a new block to the database.
     */
    void createBlock();

    /**
     * Get all blocks.
     *
     * @return List of all blocks
     */
    List<T> getBlocks();

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
