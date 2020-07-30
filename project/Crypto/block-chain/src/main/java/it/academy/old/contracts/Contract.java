package it.academy.old.contracts;


public abstract class Contract {
    final int providerSignature;
    public enum ContractType{
        TRANSFER,
        PURCHASE,
        SALE,
        RENT,
        EXCHANGE,
        SERVICE;
    }


    final ContractType contractType;
    public abstract static class Builder<T extends Builder<T>> {
        private ContractType contractType;
        private final int providerSignature;
        public Builder (ContractType type,int providerSignature) {
            this.contractType = type;
            this.providerSignature = providerSignature;
        }

        public abstract Contract build();

        protected abstract T self();
    }

    protected Contract(Builder<?> builder) {
        this.contractType = builder.contractType;
        this.providerSignature = builder.providerSignature;
    }
}
