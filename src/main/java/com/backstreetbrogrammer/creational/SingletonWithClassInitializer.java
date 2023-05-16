package com.backstreetbrogrammer.creational;

public class SingletonWithClassInitializer {

    // write once when the class is initialized - always thread safe
    private static SingletonWithClassInitializer instance = new SingletonWithClassInitializer();

    private SingletonWithClassInitializer() {
    }

    public static SingletonWithClassInitializer getInstance() {
        return instance; // no lock required for reads by multiple threads
    }

}
