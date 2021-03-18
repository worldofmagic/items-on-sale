package com.rbc.itemsonsale.service.Impl;

import com.rbc.itemsonsale.model.Item;
import com.rbc.itemsonsale.repository.ItemOnSaleRepository;
import com.rbc.itemsonsale.service.ItemOnSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemOnSaleServiceImpl implements ItemOnSaleService {

    @Autowired
    ItemOnSaleRepository itemOnSaleRepository;

    @Override
    public List<Item> getItemOnSaleWithUserId(int userId) {
        List<Item> itemsFromOrders = itemOnSaleRepository.findAllItemsFromOrderByCategory(userId);
        System.out.println("part1 :" + itemsFromOrders.toString());
        List<Item> itemsFromWishList = itemOnSaleRepository.findAllItemsFromWishlist(userId);
        System.out.println("part2 :" + itemsFromWishList.toString());
        List<Item> itemsTopRating = itemOnSaleRepository.findItemsByTopRatingFromOtherUser(userId);
        System.out.println("part3 :" + itemsTopRating.toString());

        List<Item> res = itemsFromOrders.stream().sorted(Comparator.comparingInt(Item::getRating)).collect(
                Collectors.toList());
        res.addAll(itemsFromWishList);
        res.addAll(itemsTopRating);
        return res;
    }
}
