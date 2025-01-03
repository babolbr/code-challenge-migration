package com.example.dummyjson.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe criada para retornar uma lista de products
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {
	
	private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
