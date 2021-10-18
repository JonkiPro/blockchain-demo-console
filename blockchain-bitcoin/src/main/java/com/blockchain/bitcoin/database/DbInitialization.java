package com.blockchain.bitcoin.database;

import com.blockchain.bitcoin.database.service.WalletServiceImpl;
import com.blockchain.core.database.DbConnector;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * A class that inicializes the database.
 */
public final class DbInitialization extends DbConnector {

    private final static String SCRIPT_CREATE_TABLES = "blockchain-bitcoin/src/main/resources/create_tables.sql";

    private final WalletServiceImpl walletService;

    /**
     * Constructor.
     */
    public DbInitialization() {
        this.walletService = new WalletServiceImpl();
    }

    /**
     * Create tables for the models.
     */
    public void initTables() {
        try {
            new ScriptRunner(this.getConnection()).runScript(new BufferedReader(new FileReader(SCRIPT_CREATE_TABLES)));
        } catch (final FileNotFoundException e) {
            System.out.println("File " + SCRIPT_CREATE_TABLES + " not found!");
            System.exit(0);
        }
    }

    /**
     * Create sample wallets.
     */
    public void createRandomWallets() {
        if(this.walletService.getWallets().isEmpty()) {
            this.walletService.createWallet("j/5AqUeiePaFpg6ZgFoREno8CVpdFRp2RIEQ3xr72Xn7XMTxI5cOBTSTgRt0yJYOe7LelOUyLXeXJFAQTp3okMVo4wiyUlgRqdSksELGdPI=");
            this.walletService.createWallet("d9x9xyuUKghZA8J66sjHl/lGU9BWowJ14y0eHUwfObhIoeXgASPUz0FYV1qNnuABWfTaBFaqA0yZGOIX+hue22DfCGfxuHwXoGWOzjGBzEg=");
            this.walletService.createWallet("V0fwT0CQZMdDpvSG2g+14OBdFE1nUzVtcTCcK2NEOdUe2Pn3GQVtM3lCTMR+Gf/xIAKm4vsS7XbAzKiB7Yhi0QdVAMuW8JFud+0ur6azgQk=");
            this.walletService.createWallet("Cb34/XA/1TYlNI3/DkaGpGJWDNUIvFFo13CPaxQVC5WQAzs51DhoHq0WGisnRSyS/qkLsW9tn7ycbG6/1fiuaBEW+8Lb6KPo9MqjM89BX04=");
        }
    }
}
