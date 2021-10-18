package com.blockchain.core.exception;

/**
 * The common exception class that represents a service failure.
 */
public class ResourceException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg human readable message
     */
    public ResourceException(String msg) {
        super(msg);
    }
}
