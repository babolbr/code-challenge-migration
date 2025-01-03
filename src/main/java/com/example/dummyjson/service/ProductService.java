package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductService {

    private WebClient webClient;
   
    @Value("${endpoint.url}")
    private String urlParametrizada;
    

    public ProductService() {
        this.webClient = WebClient.create("https://dummyjson.com");
    }
    
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }
    
   
    public List<Product> getAllProducts() {
    	this.webClient =  WebClient.create(urlParametrizada);
    	Mono<ProductResponse> response = webClient.get().uri("/products")
    			  .accept(MediaType.APPLICATION_JSON)
    			  .retrieve()
    			  .bodyToMono(ProductResponse.class).log();
    	
    	//Adaptacao realizada pois o json retorna uma lista de products, 
    	//e a conversao para Product[] estava apresentando problema
    	ProductResponse product = response.block();

    	return product.getProducts();
    }
    
    public Product getProductById(Long id) {
    	this.webClient =  WebClient.create(urlParametrizada);
   	 return this.webClient.get()
	            .uri("/products/"+id)
	            .retrieve()
	            .bodyToMono(Product.class).block();
    }
}
