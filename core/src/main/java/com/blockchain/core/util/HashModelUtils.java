package com.blockchain.core.util;

import com.blockchain.core.database.model.Block;
import com.blockchain.core.database.model.HashModel;
import com.blockchain.core.database.model.Transaction;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class utils for hashing.
 */
public final class HashModelUtils {

    /**
     * Get the block hash.
     *
     * @param obj An object inheriting from {@link com.blockchain.core.database.model.HashModel} class
     * @return The block hash
     */
    public static String getHash(final HashModel obj) {
        try {
            return hexToString(
                    MessageDigest
                            .getInstance("SHA-256")
                            .digest(getByteArray(obj)));
        } catch (final NoSuchAlgorithmException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return null;
    }

    /**
     * Convert a byte array to a String.
     *
     * @param hex The byte array
     * @return String from a byte array
     */
    private static String hexToString(final byte[] hex) {
        final BigInteger bi = new BigInteger(1, hex);
        final StringBuilder hexString = new StringBuilder(bi.toString());

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    /**
     * Get the byte array.
     *
     * @param obj An object inheriting from {@link com.blockchain.core.database.model.HashModel} class
     * @return The byte array
     */
    private static byte[] getByteArray(final HashModel obj) {
        if(obj instanceof final Block block)
            return (block.getPreviousHash()
                + block.getData()
                + block.getTimestamp()
                + block.getNonce())
                .getBytes(StandardCharsets.UTF_8);
        else if(obj instanceof final Transaction transaction)
            return (transaction.getAddressSender()
                    + transaction.getAddressRecipient()
                    + transaction.getData()
                    + transaction.getTimestamp()
                    + transaction.getHashBlock())
                    .getBytes(StandardCharsets.UTF_8);
        return null;
    }
}
