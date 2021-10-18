package com.blockchain.bitcoin.database.repository;

import com.blockchain.bitcoin.database.model.TransactionBitcoin;
import com.blockchain.core.database.DbConnector;
import com.blockchain.core.database.repository.TransactionRepository;
import com.blockchain.core.util.BlockchainException;
import com.blockchain.core.util.TransactionFieldsEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementation of the Transaction Repository.
 */
public class TransactionRepositoryImpl extends DbConnector implements TransactionRepository<TransactionBitcoin> {

    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Constructor.
     */
    public TransactionRepositoryImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionBitcoin createTransaction(final TransactionBitcoin transaction) {
        try {
            preparedStatement
                    = this.getConnection().prepareStatement
                    ("INSERT INTO bitcoin_transactions(hash, hash_block, address_sender, address_recipient, data, timestamp, amount, fees) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, transaction.getHash());
            preparedStatement.setString(2, transaction.getHashBlock());
            preparedStatement.setString(3, transaction.getAddressSender());
            preparedStatement.setString(4, transaction.getAddressRecipient());
            preparedStatement.setString(5, transaction.getData());
            preparedStatement.setLong(6, transaction.getTimestamp());
            preparedStatement.setBigDecimal(7, transaction.getAmount());
            preparedStatement.setBigDecimal(8, transaction.getFees());

            preparedStatement.execute();
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return this.getTransaction(transaction.getHash());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionBitcoin getTransaction(final String hash) {
        try {
            preparedStatement
                    = this.getConnection().prepareStatement
                    ("SELECT id, hash, hash_block, address_sender, address_recipient, data, timestamp, amount, fees FROM bitcoin_transactions WHERE hash = ?");
            preparedStatement.setString(1, hash);
            final ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return this.toTransactionBitcoin(resultSet);
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<TransactionBitcoin> getTransactions() {
        final ArrayList<TransactionBitcoin> listTransactions = new ArrayList<>();
        try {
            statement = this.getConnection().createStatement();

            final ResultSet resultSet
                    = statement.executeQuery("SELECT hash, hash_block, address_sender, address_recipient, data, timestamp, amount, fees FROM bitcoin_transactions");

            while(resultSet.next()) {
                listTransactions.add(this.toTransactionBitcoin(resultSet));
            }
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return listTransactions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<TransactionBitcoin> getTransactions(final TransactionFieldsEnum field, final String data) {
       final String querySql = "SELECT id, hash, hash_block, address_sender, address_recipient, data, timestamp, amount, fees FROM bitcoin_transactions WHERE "
               + switch (field) {
           case HASH_BLOCK -> "hash_block = ?";
           case ADDRESS_SENDER -> "address_sender = ?";
           case ADDRESS_RECIPIENT -> "address_recipient = ?";
       };
       final ArrayList<TransactionBitcoin> listTransactions = new ArrayList<>();

        try {
            preparedStatement = this.getConnection().prepareStatement(querySql);
            preparedStatement.setString(1, data);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                listTransactions.add(this.toTransactionBitcoin(resultSet));
            }
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return listTransactions;
    }

    /**
     * Map ResultSet to the TransactionBitcoin model.
     *
     * @param rs The result from the SQL query
     * @return The TransactionBitcoin model
     * @throws SQLException if there is a database access error or syntax error
     */
    private TransactionBitcoin toTransactionBitcoin(final ResultSet rs) throws SQLException {
        return new TransactionBitcoin(
                rs.getString("hash"),
                rs.getString("hash_block"),
                rs.getString("address_sender"),
                rs.getString("address_recipient"),
                rs.getString("data"),
                rs.getLong("timestamp"),
                rs.getBigDecimal("amount"),
                rs.getBigDecimal("fees"));
    }
}
