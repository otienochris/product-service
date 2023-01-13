package ke.or.expd.productservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    private BigDecimal id;
    private String name;
    private String description;
    private Double price;
}
