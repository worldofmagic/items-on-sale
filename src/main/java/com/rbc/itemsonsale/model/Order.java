package com.rbc.itemsonsale.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "order_id")
    private int orderId;
    private int uid;
    @Column(name = "item_id")
    private int itemId;
    private int number;
    @Column(name = "item_category")
    private String itemCategory;
}
