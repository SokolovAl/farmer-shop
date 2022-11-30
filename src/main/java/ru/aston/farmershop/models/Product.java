package ru.aston.farmershop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ProductType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
