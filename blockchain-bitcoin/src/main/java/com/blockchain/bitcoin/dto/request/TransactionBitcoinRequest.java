package com.blockchain.bitcoin.dto.request;

import com.blockchain.bitcoin.controller.BlockchainBitcoinController;
import com.blockchain.core.dto.request.TransactionRequest;

import java.math.BigDecimal;

/**
 * Fields representing all the values users can set when creating a new Transaction resource.
 */
public class TransactionBitcoinRequest extends TransactionRequest {
    private final BigDecimal fees;

    /**
     * Constructor.
     *
     * @param addressSender The transaction sender`s address
     * @param addressRecipient The transaction recipient`s address
     * @param data The transaction data
     * @param amount The transaction amount
     */
    public TransactionBitcoinRequest(
            final String addressSender,
            final String addressRecipient,
            final String data,
            final BigDecimal amount
    ) {
        super(addressSender, addressRecipient, data, amount);
        this.fees = BlockchainBitcoinController.getInstance().getFees();
    }

    /**
     * Get the transaction fees.
     *
     * @return The transaction fees
     */
    public BigDecimal getFees() {
        return fees;
    }
}
