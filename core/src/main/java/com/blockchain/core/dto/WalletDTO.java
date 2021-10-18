package com.blockchain.core.dto;

import com.blockchain.core.database.model.Transaction;
import com.blockchain.core.util.TextColor;
import com.blockchain.core.util.CryptocurrencyCode;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Read only data transfer object representing a Wallet.
 */
public class WalletDTO {
    private final long id;
    private final String address;
    private final CryptocurrencyCode code;
    private final BigDecimal amount;

    private final ArrayList<? extends Transaction> transactionsSent;
    private final ArrayList<? extends Transaction> transactionsReceived;

    /**
     * Constructor.
     *
     * @param builder Internal class builder object
     */
    private WalletDTO(final Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.code = builder.code;
        this.amount = builder.amount;
        this.transactionsSent = builder.transactionsSent;
        this.transactionsReceived = builder.transactionsReceived;
    }

    /**
     * Get the wallet ID.
     *
     * @return The wallet ID.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Get the wallet address.
     *
     * @return The wallet address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Get the wallet code.
     *
     * @return The wallet code.
     */
    public CryptocurrencyCode getCode() {
        return this.code;
    }

    /**
     * Get the wallet amount.
     *
     * @return The wallet amount.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Immutable data-only class.
     */
    public record Builder(long id, String address, CryptocurrencyCode code, BigDecimal amount,
                          ArrayList<? extends Transaction> transactionsSent, ArrayList<? extends Transaction> transactionsReceived) {

        /**
         * Get the wallet.
         *
         * @return The wallet
         */
        public WalletDTO builder() {
            return new WalletDTO(this);
        }
    }

    /**
     * Override the toString() method.
     *
     * @return Textual representation of an object
     */
    @Override
    public String toString() {
        return id + ": " + TextColor.YELLOW
                + address + TextColor.RESET + " " + code + ": " + TextColor.YELLOW
                + amount.doubleValue() + TextColor.RESET + "; "
                + "Transactions sent: " + TextColor.YELLOW + transactionsSent.size() + TextColor.RESET + " received: " + TextColor.YELLOW + transactionsReceived.size() + TextColor.RESET;
    }
}
