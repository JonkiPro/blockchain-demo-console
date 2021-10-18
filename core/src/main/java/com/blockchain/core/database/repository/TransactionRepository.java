package com.blockchain.core.database.repository;

import com.blockchain.core.database.model.Transaction;
import com.blockchain.core.util.TransactionFieldsEnum;

import java.util.ArrayList;

/**
 * Transaction repository.
 *
 * @param <T> Objects inheriting from {@link com.blockchain.core.database.model.Transaction} class
 */
public interface TransactionRepository<T extends Transaction> {

    /**
     * Add a new transaction to the database.
     *
     * @param transaction New transaction to add to database
     * @return The transaction created
     */
    T createTransaction(final T transaction);

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
    ArrayList<T> getTransactions();

    /**
     * Get all transactions.
     *
     * @param field The transaction field
     * @param data The data
     * @return List of all transactions
     */
    ArrayList<T> getTransactions(final TransactionFieldsEnum field, final String data);
}
