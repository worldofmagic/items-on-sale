package com.rbc.itemsonsale.service;

import com.rbc.itemsonsale.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemOnSaleService {
    public List<Item> getItemOnSaleWithUserId(int userId);
}
