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
        List<Item> itemsFromWishList = itemOnSaleRepository.findAllItemsFromWishlist(userId);
        List<Item> itemsTopRating = itemOnSaleRepository.findItemsByTopRating();

        List<Item> res = itemsFromOrders.stream().sorted(Comparator.comparingInt(Item::getRating)).collect(
                Collectors.toList());
        res.addAll(itemsFromWishList);
        res.addAll(itemsTopRating);
        return res;
    }
}
