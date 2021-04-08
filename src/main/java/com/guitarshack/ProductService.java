package com.guitarshack;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private Request request;

    public ProductService(Request request) {
        this.request = request;
    }

    Product getProduct(int productId) {

        String baseURL = "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product";
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("id", productId);
        }};
        String paramString = "?";

        for (String key : params.keySet()) {
            paramString += key + "=" + params.get(key).toString() + "&";
        }
        String result = request.request(baseURL, paramString);
        return new Gson().fromJson(result, Product.class);
    }
}