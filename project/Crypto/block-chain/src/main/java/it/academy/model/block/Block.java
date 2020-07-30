package it.academy.model.block;

import com.sun.istack.NotNull;
import it.academy.model.transaction.Transaction;
import it.academy.old.exceptions.BlockChainException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
 *@ block version 0x1
 */


@Getter
@Entity
public class Block {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid2")
    private String blockId;

    @Column(name = "version")
    private final String version;

    @Column(name = "timestamp")
    private final long timestamp;

    @Column(name = "block_hash")
    private String hash;

    @Setter
    @Column(name = "previous_block_hash")
    private String previousHash;

    @Column(name = "transaction_merkle_root")
    private String transactionsMerkleRoot; // merkle_root транзакций

    @Setter
    @Column(name = "nonce")
    private long nonce;

    @Column(name = "transactions_count")
    private final int transactionCount; // количество транзакций в блоке


    @OneToMany(mappedBy = "block",cascade = CascadeType.ALL)
    List<Transaction> transactions; //список транзакций первой должна быть generic транзакция (Награда за блок)


    public Block (List<Transaction> transactions,Long timestamp) {
        this.version = "0x1";
        if (timestamp == null) throw new BlockChainException("illegal timestamp");
        this.timestamp = System.currentTimeMillis();
        this.transactions = transactions;
        this.transactionCount = transactions.size();
        this.hash = hash(); // всегда последней инициализируем

    }

    private Block (Builder builder) {
        this.version = builder.version;
        this.timestamp = builder.timestamp;
        this.transactions = builder.transactions;
        this.transactionCount = builder.transactionCount;
        this.nonce = builder.nonce;
        this.previousHash = builder.previousHash;
        this.transactionsMerkleRoot = builder.transactionsMerkleRoot;
        this.hash = hash();


    }

    public String hash(){
        String concatString = this.version + this.timestamp + this.previousHash + this.transactions+this.nonce;
        byte[] stringBytes = concatString.getBytes(StandardCharsets.UTF_8);
        MessageDigest digest;
        String encoded = null;
        try {
            digest = MessageDigest.getInstance("SHA3-512");
            byte[] byteHash = digest.digest(stringBytes);
            encoded = Base64.getEncoder().encodeToString(byteHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.hash = encoded;
        return encoded;
    }

    public static class Builder{
        private final String version;
        private long timestamp;
        private String previousHash;
        private String transactionsMerkleRoot; // merkle_root транзакций
        private final long nonce;
        private int transactionCount; // количество транзакций в блоке
        private List<Transaction> transactions;

        public Builder(){
            this.version= "0x1";
            this.nonce =0;
        }

        public Builder setCurrentTimeStamp(){
            this.timestamp = System.currentTimeMillis();
            return this;
        }

        public Builder setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
            this.transactionCount = transactions.size();
            return this;
        }

        public Builder setMerkleHashRoot(String hashRoot){
            this.transactionsMerkleRoot = hashRoot;
            return this;
        }

        public Builder setPreviousHash(String previousHash){
            this.previousHash = previousHash;
            return this;
        }

        public Block build(){
            return new Block(this);
        }

    }




}
