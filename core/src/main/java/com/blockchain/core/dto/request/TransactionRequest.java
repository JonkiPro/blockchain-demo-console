package com.blockchain.core.dto.request;

import java.math.BigDecimal;

/**
 * Fields representing all the values users can set when creating a new Transaction resource.
 */
public class TransactionRequest {
    private final String addressSender;
    private final String addressRecipient;
    private final String data;
    private final BigDecimal amount;

    /**
     * Constructor.
     *
     * @param addressSender The transaction sender`s address
     * @param addressRecipient The transaction recipient`s address
     * @param data The transaction data
     * @param amount The transaction amount
     */
    public TransactionRequest(final String addressSender, final String addressRecipient, final String data, final BigDecimal amount) {
        this.addressSender = addressSender;
        this.addressRecipient = addressRecipient;
        this.data = data;
        this.amount = amount;
    }

    /**
     * Get the transaction sender`s address
     *
     * @return The transaction sender`s address
     */
    public String getAddressSender() {
        return this.addressSender;
    }

    /**
     * Get the transaction recipient`s address
     *
     * @return The transaction recipient`s address
     */
    public String getAddressRecipient() {
        return this.addressRecipient;
    }

    /**
     * Get the transaction data.
     *
     * @return The transaction data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Get the transaction amount.
     *
     * @return The transaction amount
     */
    public BigDecimal getAmount() {
        return this.amount;
    }
}
