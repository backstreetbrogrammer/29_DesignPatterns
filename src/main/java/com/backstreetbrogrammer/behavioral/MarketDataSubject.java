package com.backstreetbrogrammer.behavioral;

public interface MarketDataSubject {
    void registerObserver(MarketDataObserver marketDataObserver);

    void notifyObservers(String marketData);
}
