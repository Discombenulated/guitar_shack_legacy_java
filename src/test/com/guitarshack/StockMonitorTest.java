package com.guitarshack;

import junit.framework.TestCase;
import org.mockito.Mockito;

public class StockMonitorTest extends TestCase {

    private static final Alert MOCK_ALERT = Mockito.mock(Alert.class);
    private static final ProductService MOCK_PRODUCT_SERVICE = Mockito.mock(ProductService.class);
    private static final SaleService MOCK_SALE_SERVICE = Mockito.mock(SaleService.class);
    private static final StockMonitor MONITOR = new StockMonitor(MOCK_ALERT, MOCK_PRODUCT_SERVICE, MOCK_SALE_SERVICE);

    private static final int PRODUCT_ID = 811;
    private static final int HIGH_QUANTITY = 1000;
    private static final int LOW_QUANTITY = 1;
    private static final Product PRODUCT = new Product(811, 53, 14);
    private static final SalesTotal SALES_TOTAL = new SalesTotal(100);

    @Override
    public void setUp() {
        Mockito.reset(MOCK_ALERT);
        Mockito.when(MOCK_PRODUCT_SERVICE.getProduct(PRODUCT_ID)).thenReturn(PRODUCT);
        Mockito.when(MOCK_SALE_SERVICE.getSalesTotal(PRODUCT)).thenReturn(SALES_TOTAL);
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