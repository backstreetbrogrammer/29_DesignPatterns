package com.backstreetbrogrammer.creational;

public class Pizza {

    private int size; // required
    private boolean cheese;
    private boolean pepperoni;
    private boolean bacon;

    public static class Builder {
        //required
        private final int size;

        //optional
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean bacon = false;

        public Builder(final int size) {
            this.size = size;
        }

        public Builder cheese(final boolean value) {
            cheese = value;
            return this;
        }

        public Builder pepperoni(final boolean value) {
            pepperoni = value;
            return this;
        }

        public Builder bacon(final boolean value) {
            bacon = value;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    private Pizza(final Builder builder) {
        size = builder.size;
        cheese = builder.cheese;
        pepperoni = builder.pepperoni;
        bacon = builder.bacon;
    }
}
