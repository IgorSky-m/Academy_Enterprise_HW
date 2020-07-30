package it.academy.old;


import it.academy.old.exceptions.BlockChainException;
import it.academy.old.interfaces.IBlock;
import it.academy.model.block.BlockHeader;
import it.academy.old.contracts.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Block implements IBlock {
    private final int blockId;
    private final int hash;
    private final int previousBlockHash;
    private BlockHeader data;
    private final List<Contract> contracts;
    private final long timestamp;

    private Block(Builder builder){
        this.blockId = (builder.previousBlock.getBlockId() + 1);
        this.contracts = new ArrayList<>();
        this.contracts.addAll(builder.contracts);
        this.previousBlockHash = builder.previousBlock.hashCode();
        this.timestamp = System.currentTimeMillis();
        this.hash = hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return blockId == block.blockId &&
                hash == block.hash &&
                previousBlockHash == block.previousBlockHash &&
                timestamp == block.timestamp &&
                Objects.equals(contracts, block.contracts);
    }

    @Override
    public int hashCode() {
        final int currentHash = Objects.hash(blockId, previousBlockHash, timestamp, Arrays.deepHashCode(contracts.toArray()));
        if (hash == 0 || hash == currentHash) return currentHash;
        throw new BlockChainException("wrong block");
    }

    public int getBlockId(){
        return this.blockId;
    }

    static class Builder {
        private final List<Contract> contracts;
        private final IBlock previousBlock;
        public Builder(IBlock previousBlock){
            this.previousBlock = previousBlock;
            this.contracts = new ArrayList<>();
        }

        public Builder addContract(Contract contract) {
            this.contracts.add(contract);
            return this;
        }
        public Builder addContracts(List<Contract> contracts){
            this.contracts.addAll(contracts);
            return this;
        }
        public Block build(){
            return new Block(this);
        }
    }


}
