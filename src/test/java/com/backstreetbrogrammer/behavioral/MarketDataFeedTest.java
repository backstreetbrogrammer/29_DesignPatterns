package com.backstreetbrogrammer.behavioral;

import org.junit.jupiter.api.Test;

public class MarketDataFeedTest {

    @Test
    void testObserver() {
        final MarketDataFeed marketDataFeed = new MarketDataFeed();
        marketDataFeed.registerObserver(new AlgoEngine());
        marketDataFeed.registerObserver(new RealTimeAnalytics());
        marketDataFeed.registerObserver(new PlotData());

        final String mdTick = String.format("Stock=%s,open=%.2f,high=%.2f,low=%.2f,close=%.2f",
                                            "AAPL", 97.23D, 100.5D, 95.76D, 99.65D);
        System.out.printf("Market Data received: %s%n", mdTick);

        marketDataFeed.notifyObservers(mdTick);
    }
}
