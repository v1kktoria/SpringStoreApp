package org.spring.store.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private int stockQuantity;
    private String imageURL;
}
