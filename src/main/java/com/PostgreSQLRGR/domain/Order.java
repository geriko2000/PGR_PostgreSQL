package com.PostgreSQLRGR.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name="order_seq", sequenceName="SEQ_ORDER", allocationSize = 1)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String client;
    @Column(nullable = false)
    private String product;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private LocalDate date;

    public Order(String client, String product, int quantity, LocalDate date) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

}
