package ke.or.expd.productservice.service.impl;

import ke.or.expd.productservice.model.dto.ProductDto;
import ke.or.expd.productservice.model.entity.Product;
import ke.or.expd.productservice.repository.ProductRepository;
import ke.or.expd.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductDto mapProductToProductDto(Product savedProduct) {
        return ProductDto.builder().description(savedProduct.getDescription()).price(savedProduct.getPrice()).id(savedProduct.getId()).name(savedProduct.getName()).build();
    }

    private static Product mapProductDtoToProduct(ProductDto productDto) {
        return Product.builder().price(productDto.getPrice()).description(productDto.getDescription()).name(productDto.getName()).build();
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        log.info("Saving a new product.");
        Product product = mapProductDtoToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        log.info("Product {} is saved.", savedProduct.getId());
        return mapProductToProductDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Retrieving a list of products");
        List<ProductDto> response = productRepository.findAll().stream().map(this::mapProductToProductDto).collect(Collectors.toList());
        if (response.isEmpty())
            log.warn("Retrieved an empty list of products");
        else log.info("Successfully retrieved a list of products.");
        return response;
    }
}
