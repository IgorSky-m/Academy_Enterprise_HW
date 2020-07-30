package it.academy.model.block;

import it.academy.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Chain {
    List<Block> chain;

    public Chain() {
        this.chain = new ArrayList<>();
        chain.add(generateGenesis());
    }

    private Block generateGenesis(){
        List<Transaction> transactions = new ArrayList<>();
        Block genesisBlock = new Block(transactions, 1596081903000L);
        genesisBlock.setPreviousHash(null);
        genesisBlock.hash();
        return genesisBlock;
    }

    public void addBlock(Block block){
        Block newBlock = block;
        String previousHash = chain.get(chain.size()-1).getHash();
        newBlock.setPreviousHash(previousHash);
        newBlock.hash();
        this.chain.add(newBlock);
    }


    public void displayChain(){
        for (int i = 0; i < chain.size(); i++) {
            System.out.println("Block: " +i+".Version: "+chain.get(i).getVersion() +". hash: "+chain.get(i).hash()+". previous block hash: "+chain.get(i).getPreviousHash() );
        }
    }


    public void isValid(){
        for (int i = chain.size()-1; i>0 ; i--) {
            boolean isValid = true;
            if (!(chain.get(i).getHash().equals(chain.get(i).hash()))){
                System.out.println("invalid");
                isValid = false;
                break;
            }

            if (!(chain.get(i).getPreviousHash().equals(chain.get(i-1).hash()))) {
                System.out.println("invalid");
                isValid = false;
                break;
            }
            if (isValid) System.out.println("valid");
        }


    }
}
