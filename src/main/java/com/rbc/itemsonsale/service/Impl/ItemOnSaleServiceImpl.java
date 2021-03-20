package com.rbc.itemsonsale.service.Impl;

import com.rbc.itemsonsale.model.Item;
import com.rbc.itemsonsale.repository.ItemOnSaleRepository;
import com.rbc.itemsonsale.service.ItemOnSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ItemOnSaleServiceImpl implements ItemOnSaleService {

    final Logger logger = LoggerFactory.getLogger(ItemOnSaleServiceImpl.class);

    @Autowired
    ItemOnSaleRepository itemOnSaleRepository;

    @Override
    public List<Item> getItemOnSaleWithUserId(int userId) {
        List<Item> itemsFromOrders = itemOnSaleRepository.findAllItemsFromOrderByCategory(userId);
        logger.info("part1 :" + itemsFromOrders.toString());
        List<Item> itemsFromWishList = itemOnSaleRepository.findAllItemsFromWishlist(userId);
        logger.info("part2 :" + itemsFromWishList.toString());
        List<Item> itemsTopRating = itemOnSaleRepository.findItemsByTopRatingFromOtherUser(userId);
        logger.info("part3 :" + itemsTopRating.toString());

        // merge three lists follow the order: itemsFromOrders -> itemsFromWishList -> itemsTopRating
        // and remove duplicates
        List<Item> res = Stream.of(itemsFromOrders, itemsFromWishList, itemsTopRating).flatMap(
                Collection::stream).distinct().collect(Collectors.toList());
        return res;
    }
}
