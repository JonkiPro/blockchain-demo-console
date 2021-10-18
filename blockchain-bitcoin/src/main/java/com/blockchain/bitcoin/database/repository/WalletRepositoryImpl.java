package com.blockchain.bitcoin.database.repository;

import com.blockchain.core.database.DbConnector;
import com.blockchain.core.database.model.Wallet;
import com.blockchain.core.database.repository.WalletRepository;
import com.blockchain.core.util.BlockchainException;
import com.blockchain.core.util.CryptocurrencyCode;
import com.blockchain.core.util.TransactionFieldsEnum;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementation of the Wallet Repository.
 */
public class WalletRepositoryImpl extends DbConnector implements WalletRepository {

    private Statement statement;
    private PreparedStatement preparedStatement;

    private final TransactionRepositoryImpl transactionRepository;

    /**
     * Constructor.
     */
    public WalletRepositoryImpl() {
        this.transactionRepository = new TransactionRepositoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createWallet(final String address) {
        try {
            preparedStatement
                    = this.getConnection().prepareStatement
                    ("INSERT INTO bitcoin_wallets(address, code, amount) "
                            + "VALUES (?, ?, ?)");
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, CryptocurrencyCode.BTC.toString());
            preparedStatement.setBigDecimal(3, new BigDecimal(100));

            preparedStatement.execute();
            return true;
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wallet getWallet(final String address) {
        try {
            preparedStatement
                    = this.getConnection().prepareStatement
                    ("SELECT id, address, code, amount FROM bitcoin_wallets WHERE address = ?");
            preparedStatement.setString(1, address);
            final ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return this.toWallet(resultSet);
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Wallet> getWallets() {
        final ArrayList<Wallet> listWallets = new ArrayList<>();

        try {
            statement = this.getConnection().createStatement();

            final ResultSet resultSet
                    = statement.executeQuery("SELECT id, address, code, amount FROM bitcoin_wallets");

            while(resultSet.next()) {
                listWallets.add(this.toWallet(resultSet));
            }

            return listWallets;
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return listWallets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateWallet(final long id, final BigDecimal newValue) {
        try {
            preparedStatement
                    = this.getConnection().prepareStatement
                    ("UPDATE bitcoin_wallets SET amount = ? WHERE id = ?");
            preparedStatement.setBigDecimal(1, newValue);
            preparedStatement.setLong(2, id);

            preparedStatement.execute();
            return true;
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return false;
    }

    /**
     * Map ResultSet to the Wallet model.
     *
     * @param rs The result from the SQL query
     * @return The Wallet model
     * @throws SQLException if there is a database access error or syntax error
     */
    private Wallet toWallet(final ResultSet rs) throws SQLException {
        return new Wallet(
                rs.getLong("id"),
                rs.getString("address"),
                CryptocurrencyCode.valueOf(rs.getString("code")),
                rs.getBigDecimal("amount"),
                this.transactionRepository.getTransactions(TransactionFieldsEnum.ADDRESS_SENDER, rs.getString("address")),
                this.transactionRepository.getTransactions(TransactionFieldsEnum.ADDRESS_RECIPIENT, rs.getString("address")));
    }
}
