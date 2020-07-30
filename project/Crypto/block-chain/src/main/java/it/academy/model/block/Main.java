package it.academy.model.block;

import it.academy.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Chain coin = new Chain();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());

        Block block = new Block(transactions, System.currentTimeMillis());

        List<Transaction> transactions1 = new LinkedList<>();
        transactions1.add(new Transaction());
        Block block1 = new Block(transactions1, System.currentTimeMillis());

        List<Transaction> transactions2 = new ArrayList<>();
        transactions2.add(new Transaction());
        transactions2.add(new Transaction());
        transactions2.add(new Transaction());

        Block block2 = new Block(transactions2, System.currentTimeMillis());

        coin.addBlock(block);
        coin.addBlock(block1);
        coin.addBlock(block2);

        coin.displayChain();

//        coin.chain.get(2).getTransactions().add(new Transaction());



        coin.isValid();

        System.out.println("----------");
        System.out.println(block.hash());

    }
}
