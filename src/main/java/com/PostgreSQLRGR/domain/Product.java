package com.PostgreSQLRGR.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name="product_seq", sequenceName="SEQ_PRODUCT", allocationSize = 1)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String material;
    @Column(nullable = false)
    private int availability;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Column(nullable = false)
    private int cost;

    public Product(String name, String type, String material, int availability, LocalDate releaseDate, int cost) {
        this.name = name;
        this.type = type;
        this.material = material;
        this.availability = availability;
        this.releaseDate = releaseDate;
        this.cost = cost;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public int getAvailability() {
        return availability;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public long getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", type=" + type + ", material=" + material +
                ", availability=" + availability + ", releaseDate=" + releaseDate + ", cost=" + cost;
    }
}
