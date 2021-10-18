package com.blockchain.bitcoin.database.service;

import com.blockchain.bitcoin.database.model.BlockBitcoin;
import com.blockchain.bitcoin.database.repository.BlockRepositoryImpl;
import com.blockchain.bitcoin.dto.BlockBitcoinDTO;
import com.blockchain.core.database.model.Block;
import com.blockchain.core.service.BlockService;
import com.blockchain.core.util.HashModelUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Implementation of the Block Service.
 */
public class BlockServiceImpl implements BlockService<BlockBitcoinDTO> {

    private final BlockRepositoryImpl blockRepository;

    /**
     * Constructor.
     */
    public BlockServiceImpl() {
        this.blockRepository = new BlockRepositoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createBlock() {
        if(!this.existsBlocks()) {
            final BlockBitcoin newBlock = new BlockBitcoin(
                    "",
                    "I`m the first block BTC!",
                    new Random().nextInt(100000));
            this.blockRepository.createBlock(newBlock);
        } else {

            final Block lastBlock = this.blockRepository.getLastBlock();

            final String target = lastBlock.getHash();
            lastBlock.setHash("");

            while (!lastBlock.getHash().equals(target)) {
                lastBlock.setHash(HashModelUtils.getHash(lastBlock));
            }

            final BlockBitcoin newBlock = new BlockBitcoin(
                    lastBlock.getHash(),
                    "I`m the block BTC!",
                    new Random().nextInt(100000));

            this.blockRepository.createBlock(newBlock);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BlockBitcoinDTO> getBlocks() {
        return this.blockRepository.getBlocks()
                .stream()
                .map(ServiceUtils::toBlockDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockBitcoinDTO getLastBlock() {
        return ServiceUtils.toBlockDTO(this.blockRepository.getLastBlock());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsBlocks() {
        return this.blockRepository.existsBlocks();
    }
}
