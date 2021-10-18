package com.blockchain.core.database;

import com.blockchain.core.util.BlockchainException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class for connecting to a database.
 */
public class DbConnector {

    private final static String DBURL = "jdbc:mysql://127.0.0.1:3306/database_blockchain";
    private final static String DBUSER = "root";
    private final static String DBPASS = "PasswordRoot1";
    private final static String DBDRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection connection;

    /**
     * Default constructor with no parameters to create the connection.
     */
    public DbConnector() {
        try {
            Class.forName(DBDRIVER);
            this.connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (final ClassNotFoundException | SQLException e) {
            BlockchainException.showMessageAndExit(e);
        }
    }

    /**
     * Get a connection.
     *
     * @return database connection
     */
    public Connection getConnection() {
        return connection;
    }
}
