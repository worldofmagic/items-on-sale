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
    @Query(value = "select ID, ITEM_NAME, IS_ON_SALE, CATEGORY from (select items.ID, items.ITEM_NAME,"
            + " items.IS_ON_SALE, items.CATEGORY, ratings.RATING from GOODS items, TESTUSERS "
            + "users, Orders orders, RATINGS ratings where :uid = users.ID and items.CATEGORY = "
            + "orders.ITEM_CATEGORY and ratings.uid = :uid and items.id = ratings.item_id order "
            + "by ratings.RATING DESC)",
           nativeQuery = true)
    List<Item> findAllItemsFromOrderByCategory(@Param("uid") int uid);

    // find all items has the same category with the items in user's wishlist
    @Query(value = "select items.ID, items.ITEM_NAME, items.IS_ON_SALE, items.CATEGORY,"
            + "from GOODS items, TESTUSERS users, WISHILIST wishlist where :uid = "
            + "users"
            + ".ID and "
            + "items.CATEGORY = wishlist.ITEM_CATEGORY",
            nativeQuery = true)
    List<Item> findAllItemsFromWishlist(@Param("uid") int uid);


    // in the rating table group by item_id, and then get avg rating for each item
    // find the max rating item
    // find the item_id for max rating item
    // find the result
    @Query(value = "select items.ID, items.ITEM_NAME, items.IS_ON_SALE, items.CATEGORY from GOODS"
            + " items where items.id = (select item_id from RATINGS where RATING = select max"
            + "(avg_rating) from (select ITEM_ID, avg(RATING) as avg_rating from RATINGS where UID"
            + " <> :uid group by ITEM_ID))",
           nativeQuery = true)
    List<Item> findItemsByTopRatingFromOtherUser(@Param("uid") int uid);


}
