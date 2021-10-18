package com.blockchain.bitcoin.database.service;

import com.blockchain.bitcoin.dto.TransactionBitcoinDTO;
import com.blockchain.bitcoin.dto.request.TransactionBitcoinRequest;
import com.blockchain.bitcoin.database.model.TransactionBitcoin;
import com.blockchain.bitcoin.database.repository.TransactionRepositoryImpl;
import com.blockchain.core.exception.ResourceException;
import com.blockchain.core.service.TransactionService;
import com.blockchain.core.util.TextColor;
import com.blockchain.core.util.TransactionFieldsEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the Transaction Service.
 */
public class TransactionServiceImpl implements TransactionService<TransactionBitcoinDTO, TransactionBitcoinRequest> {

    private final TransactionRepositoryImpl transactionRepository;
    private final BlockServiceImpl blockService;
    private final WalletServiceImpl walletService;

    /**
     * Constructor.
     */
    public TransactionServiceImpl() {
        this.transactionRepository = new TransactionRepositoryImpl();
        this.blockService = new BlockServiceImpl();
        this.walletService = new WalletServiceImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionBitcoinDTO createTransaction(final TransactionBitcoinRequest transactionRequest) {
        final TransactionBitcoin transaction = new TransactionBitcoin(
                this.blockService.getLastBlock().getHash(),
                transactionRequest.getAddressSender(),
                transactionRequest.getAddressRecipient(),
                transactionRequest.getData(),
                transactionRequest.getAmount(),
                transactionRequest.getFees()
        );

        if(transactionRequest.getAmount().doubleValue() > 0)
            this.walletService.makeTransaction(
                    transactionRequest.getAddressSender(),
                    transactionRequest.getAddressRecipient(),
                    transactionRequest.getAmount());
        else
            throw new ResourceException("\r\n" + TextColor.RED + "SPECIFY THE APPROPRIATE VALUE!" + TextColor.RESET);

        return ServiceUtils.toTransactionBitcoinDTO(this.transactionRepository.createTransaction(transaction));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionBitcoinDTO getTransaction(final String hash) {
        return ServiceUtils.toTransactionBitcoinDTO(this.transactionRepository.getTransaction(hash));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TransactionBitcoinDTO> getTransactions() {
        return this.transactionRepository.getTransactions()
                .stream()
                .map(ServiceUtils::toTransactionBitcoinDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TransactionBitcoinDTO> getTransactions(final TransactionFieldsEnum field, final String hashBlock) {
        return this.transactionRepository.getTransactions(field, hashBlock)
                .stream()
                .map(ServiceUtils::toTransactionBitcoinDTO)
                .collect(Collectors.toList());
    }
}
