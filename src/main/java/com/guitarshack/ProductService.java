package com.guitarshack;public class ProductService{private final com.guitarshack.StockMonitor stockMonitor;	public ProductService(com.guitarshack.StockMonitor stockMonitor)	{		this.stockMonitor = stockMonitor;	}private com.guitarshack.Product getProduct(int productId) {

        java.lang.String baseURL = "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product";
        java.util.Map<java.lang.String,java.lang.Object> params = new java.util.HashMap<java.lang.String,java.lang.Object>() {{
            stockMonitor.put("id", productId);
        }};
        java.lang.String paramString = "?";

        for (java.lang.String key : params.keySet()) {
            paramString += key + "=" + params.get(key).toString() + "&";
        }
        java.lang.String result = stockMonitor.getRequest().request(baseURL, paramString);
        com.guitarshack.Product product = new com.google.gson.Gson().fromJson(result, com.guitarshack.Product.class);
        return product;
    }}