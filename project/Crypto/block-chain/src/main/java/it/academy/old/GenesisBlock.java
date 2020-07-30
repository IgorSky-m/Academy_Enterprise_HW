package it.academy.old;

import it.academy.old.interfaces.IBlock;

import java.util.Objects;

public enum GenesisBlock {
    INSTANCE;

    public Genesis getInstance(){
        return Genesis.genesisBlock;
    }

    private static class Genesis implements IBlock{
        private static final Genesis genesisBlock =  new Genesis();
        private final int blockId;
        private final int hash;
        private final long timestamp;

        private Genesis(){
            this.blockId = 0;
            this.hash = hashCode();
            this.timestamp = System.currentTimeMillis();
        }




        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Genesis that = (Genesis) o;
            return blockId == that.blockId &&
                    hash == that.hash &&
                    timestamp == that.timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(blockId, timestamp);
        }

        @Override
        public int getBlockId(){
            return this.blockId;
        }
    }
}
