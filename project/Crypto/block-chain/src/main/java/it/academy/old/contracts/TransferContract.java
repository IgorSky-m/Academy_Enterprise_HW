package it.academy.old.contracts;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class TransferContract extends Contract {
    private final EnumMap<ContractParty, Set<Integer>> contractParties;
    private final EnumMap<Rule, Map<Integer, BigDecimal>> ruleMap;


    private TransferContract(Builder builder) {
        super(builder);
        this.ruleMap=new EnumMap<>(Rule.class);
        this.contractParties = builder.contractParties.clone();
    }

    private enum Rule {
        VALUE;
    }

    private enum ContractParty{
        PROVIDER,
        RECIPIENT;
    }

    public static class Builder extends Contract.Builder<Builder> {
        private final EnumMap<ContractParty,Set<Integer>> contractParties;
        public Builder(int providerSignature) {
            super(ContractType.TRANSFER,providerSignature);
            contractParties = new EnumMap<>(ContractParty.class);
            Set<Integer> providerSignatures = new LinkedHashSet<>();
            Set<Integer> recipientSignatures = new LinkedHashSet<>();
            providerSignatures.add(providerSignature);
            contractParties.put(ContractParty.PROVIDER,providerSignatures);
            contractParties.put(ContractParty.RECIPIENT,recipientSignatures);
        }

        public Builder addRecipient (int recipientSignature) {
            contractParties.get(ContractParty.RECIPIENT).add(recipientSignature);
            return this;
        }

        @Override
        public Contract build() {
            return new TransferContract(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }


}
