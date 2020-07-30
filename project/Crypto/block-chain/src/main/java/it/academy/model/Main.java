package it.academy.model;

import it.academy.model.block.Block;
import it.academy.model.transaction.Transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            Transaction transaction = new Transaction();
            transactions.add(transaction);
        }


        Block block = new Block(transactions,System.currentTimeMillis());
        int result;
        long start = System.currentTimeMillis();
        long end;
        System.out.println(transactions.size());






    }

}
