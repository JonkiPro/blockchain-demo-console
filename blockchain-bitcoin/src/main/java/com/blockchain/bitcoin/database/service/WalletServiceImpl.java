package com.blockchain.bitcoin.database.service;

import com.blockchain.bitcoin.database.repository.WalletRepositoryImpl;
import com.blockchain.bitcoin.controller.BlockchainBitcoinController;
import com.blockchain.core.database.model.Wallet;
import com.blockchain.core.dto.WalletDTO;
import com.blockchain.core.exception.ResourceException;
import com.blockchain.core.service.WalletService;
import com.blockchain.core.util.TextColor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the Wallet Service.
 */
public class WalletServiceImpl implements WalletService {

    private final WalletRepositoryImpl walletRepository;

    /**
     * Constructor.
     */
    public WalletServiceImpl() {
        this.walletRepository = new WalletRepositoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createWallet(final String address) {
        return this.walletRepository.createWallet(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WalletDTO getWallet(final String address) {
        return ServiceUtils.toWalletDTO(this.walletRepository.getWallet(address));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WalletDTO> getWallets() {
        return this.walletRepository.getWallets()
                .stream()
                .map(ServiceUtils::toWalletDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeTransaction(final String addressFrom, final String addressTo, final BigDecimal amount) {
        final Wallet sender = this.walletRepository.getWallet(addressFrom);
        if(amount.add(BlockchainBitcoinController.getInstance().getFees()).doubleValue() >= sender.amount().doubleValue())
            throw new ResourceException("\r\n" + TextColor.RED + "NOT ENOUGH MONEY IN YOUR WALLET! YOU HAVE " + sender.amount().doubleValue() + " BTC!" + TextColor.RESET);
        final Wallet recipient = this.walletRepository.getWallet(addressTo);

        this.walletRepository.updateWallet(sender.id(), new BigDecimal(sender.amount().subtract(amount).subtract(BlockchainBitcoinController.getInstance().getFees()).toString()));
        this.walletRepository.updateWallet(recipient.id(),  new BigDecimal(recipient.amount().add(amount).toString()));
    }
}
