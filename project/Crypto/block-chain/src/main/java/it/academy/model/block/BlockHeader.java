package it.academy.model.block;



public class BlockHeader {
    int blockVersion;     //текущая версия блока
    int previousBlockHash; // хеш предыдущего блока
    int transactionsHash; // merkle_root транзакций
    long timestamp;       //время создания блока
    int bits;             //только положительные
    int nonce;            // только положительные .. это число, хеш при котором нужно искать хеш функцию ,которая ниже (итерационно 0,1,2)


}
