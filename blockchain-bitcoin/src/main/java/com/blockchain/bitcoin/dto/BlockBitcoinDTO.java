package com.blockchain.bitcoin.dto;

import com.blockchain.core.database.model.Transaction;
import com.blockchain.core.dto.BlockDTO;
import com.blockchain.core.util.TextColor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BlockBitcoinDTO extends BlockDTO {

    /**
     * Constructor.
     *
     * @param builder Internal class builder object
     */
    private BlockBitcoinDTO(final BlockBitcoinDTO.Builder builder) {
        super(builder);
    }

    /**
     * A builder to create block bitcoin.
     */
    public static class Builder extends BlockDTO.Builder {

        /**
         * Constructor which has required fields.
         *
         * @param id The block ID
         * @param hash The block hash
         * @param data Data of the block
         * @param timestamp The block timestamp
         * @param nonce The block nonce
         * @param transactions The block transactions
         */
        public Builder(
                final long id,
                final String hash,
                final String data,
                final long timestamp,
                final long nonce,
                final ArrayList<? extends Transaction> transactions
        ) {
            super(id, hash, data, timestamp, nonce, transactions);
        }

        /**
         * Get the new block bitcoin.
         *
         * @return The new block bitcoin
         */
        public BlockBitcoinDTO builder() {
            return new BlockBitcoinDTO(this);
        }
    }

    /**
     * Override the toString() method.
     *
     * @return Textual representation of an object
     */
    @Override
    public String toString() {
        return "Date: " + TextColor.YELLOW + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(this.getTimestamp()) + TextColor.RESET
                + " Id: " + TextColor.YELLOW + this.getId() + TextColor.RESET
                + " Hash: " + TextColor.YELLOW + this.getHash() + TextColor.RESET
                + " Data: " + TextColor.YELLOW + this.getData() + TextColor.RESET
                + " Number of transactions: " + TextColor.YELLOW + this.getTransactions().size() + TextColor.RESET
                + " Nonce: " + TextColor.YELLOW + this.getNonce() + TextColor.RESET;
    }
}
