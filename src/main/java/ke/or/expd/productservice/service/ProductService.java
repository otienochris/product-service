package ke.or.expd.productservice.service;

import jakarta.transaction.Transactional;
import ke.or.expd.productservice.model.dto.ProductDto;
import ke.or.expd.productservice.model.entity.Product;

import java.util.List;

@Transactional
public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();
}
