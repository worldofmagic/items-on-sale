package com.rbc.itemsonsale.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "wishlist_id")
    private int wishlistId;
    private int uid;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "item_category")
    private String itemCategory;
}
