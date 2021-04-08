package com.guitarshack;

public class StockMonitor {
    private final Alert alert;
    private final ProductService productService;
    private final SaleService saleService;

    public StockMonitor(Alert alert, ProductService productService, SaleService saleService) {
        this.alert = alert;
        this.productService = productService;
        this.saleService = saleService;
    }

    public void productSold(int productId, int amountSold) {

        Product product = productService.getProduct(productId);

        if(stockIsLow(product, amountSold))
            alert.send(product);
    }

    private boolean stockIsLow(Product product, int quantity) {
        SalesTotal totalSoldLastMonth = saleService.getSalesTotal(product);
        return product.getStock() - quantity <= (int) ((double) (totalSoldLastMonth.getTotal() / 30) * product.getLeadTime());
    }
}
