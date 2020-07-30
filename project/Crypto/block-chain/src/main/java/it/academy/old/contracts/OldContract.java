package it.academy.old.contracts;

import it.academy.old.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OldContract {
    final Set<Integer> signatures;

    private OldContract(Builder builder) {
        this.signatures = Set.copyOf(builder.signatures);
    }


    static class Builder {
        final Set<Integer> signatures;
        final List<Rule> rules;
        private ContractType type;
        public Builder(int contractProviderSignature, ContractType type){
            this.signatures = new HashSet<>();
            this.rules = new ArrayList<>();
            this.signatures.add(contractProviderSignature);
            this.type = type;
        }

        public Builder addPartyContractSignature(int signature){
            this.signatures.add(signature);
            return this;
        }


    }



}
