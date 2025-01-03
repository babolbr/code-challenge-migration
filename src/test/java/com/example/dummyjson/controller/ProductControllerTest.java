package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	//Para o SpringBotTest usa autowired
	@Autowired
    private ProductController productController;


    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Essence Mascara Lash Princess");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Eyeshadow Palette with Mirror");

        List<Product> products = Arrays.asList(product1, product2);
        //Removidas validacoes com Mokito
        //when(productService.getAllProducts()).thenReturn(products);

        List<Product> result = productController.getAllProducts();
        assertEquals(30, result.size());
        assertEquals(product1.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");

        Product result = productController.getProductById(1L);
        assertEquals("Essence Mascara Lash Princess", result.getTitle());
    }
}
