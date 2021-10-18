package com.blockchain.core.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class utils for seed.
 */
public final class SeedUtils {

    private static final String WORDS = "core/src/main/resources/words.txt";

    /**
     * Generate a seed.
     *
     * @return List of phrases.
     */
    public static ArrayList<String> generate() {
        try {
            final BufferedReader bf = new BufferedReader(new FileReader(WORDS));
            final Integer[] randomNumbers = new Integer[24];
            final ArrayList<String> words = new ArrayList<>();
            for(int i = 0; i < 24; i++) {
                randomNumbers[i] = new Random().nextInt(466466);
            }
            String line;
            Integer lineIterator = 0;
            while(!(line = bf.readLine()).isBlank()) {
                lineIterator++;
                if(List.of(randomNumbers).contains(lineIterator))
                    words.add(line);
                if(words.size() == 24)
                    return words;
            }
        } catch (final IOException e) {
            BlockchainException.showMessageAndExit(e);
        }
        return new ArrayList<>();
    }
}
