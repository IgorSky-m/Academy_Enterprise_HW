package it.academy.model.transaction;

import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class Transaction {

    @Getter
    private String hash; // id транзакции. Все транзакции представлены в 16ричном формате. , которые хешируются для получения id транщакции

    private int version; // версия
    private int providerWalletId; // адрес отправителя
    private int receiverWalletId; // id кошелька получателя
    private int publicKeyProvider; // публичный ключ отправителя
    private int value; // сумма транзакции
    private List<Input> inputs; //лист входов
    private List<Output> outputs; // лист выходов

    public Transaction (){
        this.hash = hash();
    }

    public long longHash(){
        return 31* Objects.hash(hash,version,receiverWalletId,publicKeyProvider,version);

    }



    private String hash(){
        String concatString = ""+this.version+this.value + this.publicKeyProvider; //Дописать, когда параметры придумаю окончательно
        byte[] stringBytes = concatString.getBytes(StandardCharsets.UTF_8);
        MessageDigest digest;
        String encoded = null;
        try {
            digest = MessageDigest.getInstance("SHA3-512");
            byte[] byteHash = digest.digest(stringBytes);
            encoded = Base64.getEncoder().encodeToString(byteHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // заменить на проброс ошибки
        }
        return encoded;
    }


}
