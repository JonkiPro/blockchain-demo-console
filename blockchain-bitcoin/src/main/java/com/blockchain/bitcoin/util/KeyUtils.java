package com.blockchain.bitcoin.util;;

import com.blockchain.core.util.BlockchainException;
import com.blockchain.core.util.TextColor;
import org.jasypt.util.text.AES256TextEncryptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Class utils for key.
 */
public final class KeyUtils {

    private static final String KEY = "Bar12345Bar12345";

    /**
     * Generate a private key.
     *
     * @param wordsSeed List of phrases
     * @return The private key
     */
    public static String generatePrivateKey(final ArrayList<String> wordsSeed) {
        final String strSeed = String.join(" ", wordsSeed);

        final byte[] hex1 = strSeed.subSequence(5,10).toString().getBytes(StandardCharsets.UTF_8);
        final byte[] hex2 = strSeed.subSequence(20,25).toString().getBytes(StandardCharsets.UTF_8);
        final byte[] hex3 = strSeed.subSequence(35,40).toString().getBytes(StandardCharsets.UTF_8);

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            System.out.println(TextColor.RED + "The provider does not support the MessageDigestSpi implementation for the specified algorithm 'SHA-256'" + TextColor.RESET);
            System.exit(0);
        }
        messageDigest.digest(strSeed.getBytes(StandardCharsets.UTF_8));

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write( hex1 );
            outputStream.write( hex2 );
            outputStream.write( hex3 );
        } catch (final IOException e) {
            BlockchainException.showMessageAndExit(e);
        }

        final BigInteger bi = new BigInteger(1, outputStream.toByteArray());
        final StringBuilder hexString = new StringBuilder(bi.toString());

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    /**
     * Encrypt the private key.
     *
     * @param keyPrivate The private key
     * @return The public key (The encrypted private key)
     */
    public static String encryptKey(final String keyPrivate) {
        final AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword(KEY);
        return aesEncryptor.encrypt(keyPrivate);
    }

    /**
     * Decrypt the public key.
     *
     * @param keyPublic The public key
     * @return The private key (The decrypted public key)
     */
    public static String decryptKey(final String keyPublic) {
        final AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword(KEY);
        return aesEncryptor.decrypt(keyPublic);
    }
}
