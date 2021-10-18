package com.blockchain.bitcoin.util;

import com.blockchain.core.util.BlockchainException;

import java.io.*;
import java.util.ArrayList;

/**
 * Class utils for locale user.
 */
public final class LocaleUserUnits {

    private static final String LOCALE_USER = "blockchain-bitcoin/src/main/resources/locale_user.txt";
    public static String LOCALE_USER_ADDRESS = null;

    /**
     * Create a local user data file.
     *
     * @param seed List of phrases
     * @param privateKey The private key
     * @param publicKey The public key
     */
    public static void saveFileWithSeedAndKeys(final ArrayList<String> seed, final String privateKey, final String publicKey) {
        try {
            final BufferedWriter bw = new BufferedWriter(new FileWriter(LOCALE_USER));
            bw.write(String.join(" ", seed));
            bw.newLine();
            bw.write("Private key:" + privateKey);
            bw.newLine();
            bw.write("Public key:" + publicKey);
            bw.close();
        } catch (final IOException e) {
            BlockchainException.showMessageAndExit(e);
        }
    }

    /**
     * Get the local user's public key.
     *
     * @return The public key
     */
    public static String geyPublicKey() {
        final BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(LOCALE_USER));

            String line;
            while(!(line = br.readLine()).isBlank()) {
                if(line.contains("Public key:")) {
                    final String substring = line.substring(11);
                    LOCALE_USER_ADDRESS = substring;
                    return substring;
                }
            }
        } catch (final IOException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return "NOT FOUND";
    }

    /**
     * Check if local user data file exists.
     *
     * @return True if local user data file exists
     */
    public static boolean existsFile() {
        return new File(LOCALE_USER).exists();
    }
}
