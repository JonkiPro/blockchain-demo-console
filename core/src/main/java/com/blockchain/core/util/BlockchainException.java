package com.blockchain.core.util;

/**
 * Class utils for exceptions.
 */
public final class BlockchainException {

    /**
     * View the exception message and close the application.
     *
     * @param e Exception
     */
    public static void showMessageAndExit(final Exception e) {
        System.out.println(
                "\r\n" + TextColor.RED
                + e.getClass().getName() + ": " + e.getMessage()
                + TextColor.RESET);
        System.exit(0);
    }
}
