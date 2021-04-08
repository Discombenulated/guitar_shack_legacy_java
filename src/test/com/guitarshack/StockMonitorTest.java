package com.guitarshack;

import junit.framework.TestCase;
import org.mockito.Mockito;

public class StockMonitorTest extends TestCase {

    @Override
    public void setUp() throws Exception {
        Mockito.reset(MOCK_ALERT);
    }

    public static final Alert MOCK_ALERT = Mockito.mock(Alert.class);
    public static final StockMonitor MONITOR = new StockMonitor(MOCK_ALERT);

    public void testAlertSentWhenStockIsLow() {
        MONITOR.productSold(811, 1000);
        Mockito.verify(MOCK_ALERT).send(Mockito.any());
    }

    public void testAlertNotSentWhenStockIsHigh() {
        MONITOR.productSold(811, 1);
        Mockito.verify(MOCK_ALERT, Mockito.never()).send(Mockito.any());
    }
}