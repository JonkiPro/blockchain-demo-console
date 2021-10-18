package com.blockchain.core.database.model;

/**
 * Representation of the hash model.
 */
public abstract class HashModel {
    private String hash;

    /**
     * Get the model hash.
     *
     * @return The model hash
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * Set the model hash.
     *
     * @param hash The model hash
     */
    public void setHash(final String hash) {
        this.hash = hash;
    }
}
