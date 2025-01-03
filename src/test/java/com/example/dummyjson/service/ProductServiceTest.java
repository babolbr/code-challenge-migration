package com.example.dummyjson.service;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import com.example.dummyjson.dto.Product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    public void testGetAllProducts()  throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Essence Mascara Lash Princess");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Eyeshadow Palette with Mirror");

        //Usando mockMVC para testar webClients
        MvcResult result =  this.mockMvc.perform(get("/api/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();
        String finalString = json.substring(1, json.length() - 1);

        Product product = objectMapper.readValue(finalString, Product.class);
        List<Product> products = objectMapper.readValue(json, new TypeReference<>(){});
        
        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals(product1.getTitle(), product.getTitle());

        assertNotNull(products);
        assertEquals(30,products.size());
        assertEquals(2L, products.get(1).getId());
        assertEquals(product2.getTitle(), products.get(1).getTitle());
    }
    
    @Test
    public void testGetProductById() throws Exception {
        mockMvc.perform(get("/api/products/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Essence Mascara Lash Princess"));
        
    }
}