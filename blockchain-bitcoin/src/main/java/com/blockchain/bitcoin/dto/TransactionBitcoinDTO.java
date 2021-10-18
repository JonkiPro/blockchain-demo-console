package com.blockchain.bitcoin.dto;

import com.blockchain.core.dto.TransactionDTO;
import com.blockchain.core.util.TextColor;

import java.math.BigDecimal;

/**
 * Read only data transfer object representing a Transaction.
 */
public class TransactionBitcoinDTO extends TransactionDTO {
    private final BigDecimal fees;

    /**
     * Constructor.
     *
     * @param builder Internal class builder object
     */
    private TransactionBitcoinDTO(final Builder builder) {
        super(builder);
        this.fees = builder.bFees;
    }

    /**
     * Get the transaction fees.
     *
     * @return The transaction fees
     */
    public BigDecimal getFees() {
        return fees;
    }

    /**
     * A builder to create transactions.
     */
    public static class Builder extends TransactionDTO.Builder {

        private BigDecimal bFees;

        /**
         * Constructor which has required fields.
         *
         * @param hash The transaction hash
         * @param hashBlock The hash of the block in which this transaction is placed
         * @param addressSender The transaction sender`s address
         * @param addressRecipient The transaction recipient`s address
         * @param data The transaction data
         * @param timestamp The transaction timestamp
         * @param amount The transaction amount
         */
        public Builder( final String hash,
                        final  String hashBlock,
                        final String addressSender,
                        final String addressRecipient,
                        final String data,
                        final long timestamp,
                        final BigDecimal amount
        ) {
            super(hash, hashBlock, addressSender, addressRecipient, data, timestamp, amount);
        }

        /**
         * Set the transaction fees.
         *
         * @param fees The transaction fees
         * @return Internal class builder object
         */
        public Builder withFees(final BigDecimal fees) {
            this.bFees = fees;
            return this;
        }

        /**
         * Get the new transaction.
         *
         * @return The new transaction
         */
        public TransactionBitcoinDTO builder() {
            return new TransactionBitcoinDTO(this);
        }
    }

    /**
     * Override the toString() method.
     *
     * @return Textual representation of an object
     */
    @Override
    public String toString() {
        return "\r\nTransaction " + TextColor.YELLOW + this.getHash() + TextColor.RESET
                + "\r\n from address "
                + TextColor.YELLOW + this.getAddressSender() + TextColor.RESET
                + " to address "
                + TextColor.YELLOW + this.getAddressRecipient() + TextColor.RESET
                + "\r\n data containing "
                + TextColor.YELLOW + (this.getData().isBlank() ? "empty" : this.getData()) + TextColor.RESET
                + " with a value of "
                + TextColor.YELLOW + this.getAmount().toString() + " BTC" + TextColor.RESET
                + " in block "
                + TextColor.YELLOW + this.getHashBlock() + TextColor.RESET
                + " with fees "
                + TextColor.YELLOW + this.getFees() + " BTC " + TextColor.RESET;
    }
}
