package com.backstreetbrogrammer.behavioral;

public class AlgoEngine implements MarketDataObserver {
    @Override
    public void notify(final String marketData) {
        // consume market data for algo trading
        System.out.println("Consumed by AlgoEngine");
    }
}
