package com.blockchain.bitcoin.database.repository;

import com.blockchain.bitcoin.database.model.BlockBitcoin;
import com.blockchain.core.database.DbConnector;
import com.blockchain.core.database.repository.BlockRepository;
import com.blockchain.core.util.BlockchainException;
import com.blockchain.core.util.TransactionFieldsEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementation of the Block Repository.
 */
public class BlockRepositoryImpl extends DbConnector implements BlockRepository<BlockBitcoin> {

    private Statement statement;
    private PreparedStatement preparedStatement;

    private final TransactionRepositoryImpl transactionRepository;

    /**
     * Constructor.
     */
    public BlockRepositoryImpl() {
        this.transactionRepository = new TransactionRepositoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createBlock(final BlockBitcoin block) {
        try {
            preparedStatement
                    = this.getConnection().prepareStatement
                    ("INSERT INTO bitcoin_blocks(hash, previous_hash, data, timestamp, nonce) "
                            + "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, block.getHash());
            preparedStatement.setString(2, block.getPreviousHash());
            preparedStatement.setString(3, block.getData());
            preparedStatement.setLong(4, block.getTimestamp());
            preparedStatement.setLong(5, block.getNonce());

            preparedStatement.execute();
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<BlockBitcoin> getBlocks() {
        final ArrayList<BlockBitcoin> listBlocks = new ArrayList<>();
        try {
            statement = this.getConnection().createStatement();

            final ResultSet resultSet
                    = statement.executeQuery("SELECT id, hash, previous_hash, data, timestamp, nonce FROM bitcoin_blocks");

            while(resultSet.next()) {
                listBlocks.add(this.toBlockBitcoin(resultSet));
            }
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return listBlocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockBitcoin getLastBlock() {
        try {
            statement = this.getConnection().createStatement();

            final ResultSet resultSet
                    = statement.executeQuery("SELECT id, hash, previous_hash, data, timestamp, nonce FROM bitcoin_blocks");

            while(resultSet.next()) {
                if(resultSet.isLast()) {
                    return this.toBlockBitcoin(resultSet);
                }
            }
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsBlocks() {
        try {
            statement = this.getConnection().createStatement();
            return statement.executeQuery("SELECT * from bitcoin_blocks").next();
        } catch (final SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return false;
    }

    /**
     * Map ResultSet to the BlockBitcoin model.
     *
     * @param rs The result from the SQL query
     * @return The BlockBitcoin model
     * @throws SQLException if there is a database access error or syntax error
     */
    private BlockBitcoin toBlockBitcoin(final ResultSet rs) throws SQLException {
        return new BlockBitcoin(
                rs.getLong("id"),
                rs.getString("hash"),
                rs.getString("previous_hash"),
                rs.getString("data"),
                rs.getLong("timestamp"),
                rs.getLong("nonce"),
                this.transactionRepository.getTransactions(TransactionFieldsEnum.HASH_BLOCK, rs.getString("hash")));
    }
}
