package com.blockchain.core.service;

import com.blockchain.core.dto.TransactionDTO;
import com.blockchain.core.dto.request.TransactionRequest;
import com.blockchain.core.util.TransactionFieldsEnum;

import java.util.List;

/**
 * Interfaces for providing persistence functions for transactions.
 *
 * @param <T> Objects inheriting from {@link com.blockchain.core.dto.TransactionDTO} class
 * @param <TR> Objects inheriting from {@link TransactionRequest} class
 */
public interface TransactionService<T extends TransactionDTO, TR extends TransactionRequest> {

    /**
     * Add a new transaction to the database.
     *
     * @param transactionRequest New transaction to add to database
     * @return The transaction created
     */
    T createTransaction(final TR transactionRequest);

    /**
     * Get the transaction by hash.
     *
     * @param hash The transaction hash
     * @return The transaction
     */
    T getTransaction(final String hash);

    /**
     * Get all transactions.
     *
     * @return List of all transactions
     */
    List<T> getTransactions();

    /**
     * Get all transactions.
     *
     * @param field The transaction field
     * @param data The data
     * @return List of all transactions
     */
    List<T> getTransactions(final TransactionFieldsEnum field, final String data);
}
