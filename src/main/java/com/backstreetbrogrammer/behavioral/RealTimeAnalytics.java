package com.backstreetbrogrammer.behavioral;

public class RealTimeAnalytics implements MarketDataObserver {
    @Override
    public void notify(final String marketData) {
        // real time analytics with market data
        System.out.println("Consumed by RealTimeAnalytics");
    }
}
