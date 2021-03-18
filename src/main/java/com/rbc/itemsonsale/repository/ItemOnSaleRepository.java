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
    @Query(value = "select items.ID, items.ITEM_NAME, items.IS_ON_SALE, items.CATEGORY,"
            + "items.RATING from ITEMS items, USERS users, ORDERS orders where :uid = users.ID and "
            + "items.CATEGORY = orders.ITEM_CATEGORY",
           nativeQuery = true)
    List<Item> findAllItemsFromOrderByCategory(@Param("uid") int uid);

    // find all items has the same category with the items in user's wishlist
    @Query(value = "select items.ID, items.ITEM_NAME, items.IS_ON_SALE, items.CATEGORY,"
            + "items.RATING from ITEMS items, USERS users, WISHILIST wishlist where :uid = users.ID and "
            + "items.CATEGORY = wishlist.ITEM_CATEGORY",
            nativeQuery = true)
    List<Item> findAllItemsFromWishlist(@Param("uid") int uid);

    @Query(value = "SELECT * from ITEMS where items.rating = (SELECT max(rating) from ITEMS WHERE"
            + " items.is_on_sale = true) "
            + "LIMIT 1",
           nativeQuery = true)
    List<Item> findItemsByTopRating();


}
