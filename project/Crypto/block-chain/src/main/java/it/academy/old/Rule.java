package it.academy.old;


import it.academy.old.contracts.ContractType;

public class Rule {
    private final ContractType type;

    public static Rule setBasicRules(ContractType type) {
        return new Rule(type);
    }

    private Rule(ContractType type) {
        this.type = type;

    }





}
