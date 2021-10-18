package com.blockchain.main;

import com.blockchain.bitcoin.controller.BlockchainBitcoinController;

/**
 * Main runtime class.
 */
public class Main {

    /**
     * Main running method.
     *
     * @param args Program arguments
     */
    public static void main(final String[] args) {
        //Creating a thread on the bitcoin blockchain
        final Thread threadBlockchainBitcoin = new Thread(BlockchainBitcoinController.getInstance());
        threadBlockchainBitcoin.start();

        //Displaying the menu
        Menu.show();
    }
}
