package com.backstreetbrogrammer.behavioral;

public class PlotData implements MarketDataObserver {
    @Override
    public void notify(final String marketData) {
        // plot the data into UI
        System.out.println("Consumed by PlotData for UI");
    }
}
