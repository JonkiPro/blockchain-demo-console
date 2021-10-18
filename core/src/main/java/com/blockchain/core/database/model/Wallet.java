package com.blockchain.core.database.model;

import com.blockchain.core.util.CryptocurrencyCode;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Representation of the wallet.
 */
public record Wallet(long id, String address, CryptocurrencyCode code,
                     BigDecimal amount, ArrayList<? extends Transaction> transactionsSent,
                     ArrayList<? extends Transaction> transactionsReceived) {
}
