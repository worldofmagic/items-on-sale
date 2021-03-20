package com.rbc.itemsonsale.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "goods")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "is_on_sale")
    private boolean isOnSale;
    private String category;
}
