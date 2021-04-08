package com.guitarshack;

import junit.framework.TestCase;
import org.mockito.Mockito;

public class StockMonitorTest extends TestCase {

    public static final Alert MOCK_ALERT = Mockito.mock(Alert.class);
    public static final StockMonitor MONITOR = new StockMonitor(MOCK_ALERT, new WebRequest());
    private static final int PRODUCT_ID = 811;
    private static final int HIGH_QUANTITY = 1000;
    private static final int LOW_QUANTITY = 1;

    @Override
    public void setUp() {
        Mockito.reset(MOCK_ALERT);
    }

    public void testAlertSentWhenStockIsLow() {
        MONITOR.productSold(PRODUCT_ID, HIGH_QUANTITY);
        Mockito.verify(MOCK_ALERT).send(Mockito.any());
    }

    public void testAlertNotSentWhenStockIsHigh() {
        MONITOR.productSold(PRODUCT_ID, LOW_QUANTITY);
        Mockito.verify(MOCK_ALERT, Mockito.never()).send(Mockito.any());
    }
}