package com.backstreetbrogrammer.behavioral;

import java.util.ArrayList;
import java.util.List;

public class MarketDataFeed implements MarketDataSubject {
    private final List<MarketDataObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(final MarketDataObserver marketDataObserver) {
        observers.add(marketDataObserver);
    }

    @Override
    public void notifyObservers(final String marketData) {
        observers.forEach(o -> o.notify(marketData));
    }
}
