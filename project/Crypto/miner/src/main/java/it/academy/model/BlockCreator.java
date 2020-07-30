package it.academy.model;


import it.academy.model.block.Block;
import it.academy.model.transaction.Transaction;
import it.academy.old.exceptions.BlockChainException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


public class BlockCreator {

    //сделать калькуляцию метод стоп , который стопит калькуляцию, иначе она бесконечно вызывает метод

    public String createBlock(){
            Block block;
            List<Transaction> transactions = requestTransactions();
            if (!validate(transactions)) throw new BlockChainException("invalid transactions");
            block = calc(transactions);

        return block.hash();
    }

    private List<Transaction> requestTransactions(){
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        return transactions;
    }

    public boolean validate(List<Transaction> transactions){

        return true;
    }

    private Block calc(List<Transaction> transactions){
        Block block = new Block.Builder()
                .setCurrentTimeStamp()
                .setTransactions(transactions)
                .setMerkleHashRoot(merkleRootHash(transactions))
                .setPreviousHash(getLashBlockHash())
                .build();
        final byte[] ruleBytes = "000".getBytes(); // 0000 пару минут ищет
        byte[] blockHashBytes;
        byte[] blockValidateBytes;
        long nonce =0;
        while (true) {
            blockHashBytes = block.getHash().getBytes();
            blockValidateBytes = Arrays.copyOf(blockHashBytes,ruleBytes.length);
            if (Arrays.equals(blockValidateBytes, ruleBytes)) break;
            System.out.println("not equals hash: "+ block.getHash());
            nonce++;
            System.out.println("nouce: "+nonce);
            if (nonce < 0) throw new BlockChainException("cant find block hash");
            block.setNonce(nonce);
            block.hash();
        }


        return block;
    }


    public String merkleRootHash(List<Transaction> transactions) { // первая проба
        int mid;
        String leftHash;
        String rightHash;
        if (transactions.size() >2) {
            mid = transactions.size() / 2;

            List<Transaction> transactionsLeft = transactions.stream()
                    .limit(mid)
                    .collect(Collectors.toList());
            List<Transaction> transactionsRight = transactions.stream()
                    .skip(mid)
                    .collect(Collectors.toList());

            leftHash = merkleRootHash(transactionsLeft);
            rightHash = merkleRootHash(transactionsRight);

            return merkleHash(leftHash.concat(rightHash));
        }

        if (transactions.size() == 1) {
            return merkleHash(transactions.get(0).getHash()
                    .concat(transactions.get(0).getHash()));
        } else if (transactions.size() == 2) {
            return merkleHash(transactions.get(0).getHash()
                    .concat(transactions.get(1).getHash()));
        } else throw new BlockChainException("illegal transactions size");


    }

    private String merkleHash(String str){
        byte[] stringBytes = str.getBytes(StandardCharsets.UTF_8);
        MessageDigest digest;
        String encoded = null;
        try {
            digest = MessageDigest.getInstance("SHA3-512");
            byte[] byteHash = digest.digest(stringBytes);
            encoded = Base64.getEncoder().encodeToString(byteHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encoded;
    }


    public String getLashBlockHash(){ // написать поход в базу
        return "123";
    }

    public static void main(String[] args) {
        BlockCreator creator = new BlockCreator();
       String s =  creator.createBlock();
        System.out.println("Equals hash: "+s);




    }









}
