package com.rbc.itemsonsale.repository;

import com.rbc.itemsonsale.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemOnSaleRepository extends CrudRepository<Item, Integer> {

    // find all the items has the same category with the items in user's history order
    @Query(value = "SELECT items.id, items.itemName FROM ITEMS items, USERS users, ORDERS orders "
            + "WHERE users.id = uid and users.id = orders.uid and "
            + "items.category = orders.item_category",
           nativeQuery = true)
    List<Item> findAllItemsFromOrderByCategory(@Param("uid") int uid);

    // find all items has the same category with the items in user's wishlist
    @Query(value = "SELECT items.id, items.item_name, items.id, items.is_on_sale, item.category, "
            + "item.rating FROM ITEMS items, USERS users, WISHLIST wishlist WHERE :uid = users.id"
            + " and users.id = wishlist.uid and items.category = wishlist.item_category",
            nativeQuery = true)
    List<Item> findAllItemsFromWishlist(@Param("uid") int uid);

    @Query(value = "SELECT * from ITEMS where items.rating = (SELECT max(rating) from ITEMS WHERE"
            + " items.is_on_sale = true) "
            + "LIMIT 1",
           nativeQuery = true)
    List<Item> findItemsByTopRating();


}
