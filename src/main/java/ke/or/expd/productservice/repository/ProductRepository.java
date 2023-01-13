package ke.or.expd.productservice.repository;

import ke.or.expd.productservice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, BigDecimal> {
}
