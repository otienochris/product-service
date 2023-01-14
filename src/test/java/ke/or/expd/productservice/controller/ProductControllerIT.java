package ke.or.expd.productservice.controller;

import ke.or.expd.productservice.BaseIT;
import ke.or.expd.productservice.model.entity.Product;
import ke.or.expd.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerIT extends BaseIT {

    public static final String API_V1_PRODUCTS = "/api/v1/products";
    private final Product product1 = Product.builder()
            .name("Product A")
            .description("Some description for product A")
            .price(16782.09)
            .build();
    private final Product product2 = Product.builder()
            .name("Product B")
            .description("Some description for product B")
            .price(26782.09)
            .build();
    @Autowired
    private ProductRepository productRepository;
    private final List<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        products.add(product1);
        products.add(product2);
    }

    @Test
    void createProduct() throws Exception {


        ResultActions response = mockMvc.perform(post(API_V1_PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product1)));


        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(product1.getName()))
                .andExpect(jsonPath("$.description").value(product1.getDescription()))
                .andExpect(jsonPath("$.price").value(product1.getPrice()));


    }

    @Test
    void getAllProducts() throws Exception {
        productRepository.saveAll(products);

        productRepository.saveAll(products);
        mockMvc.perform(get(API_V1_PRODUCTS).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(products.size()));
    }


}