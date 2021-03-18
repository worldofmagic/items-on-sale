package com.rbc.itemsonsale.controller;

import com.rbc.itemsonsale.model.Item;
import com.rbc.itemsonsale.service.ItemOnSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemOnSaleController {

    @Autowired
    private ItemOnSaleService itemOnSaleService;

    @RequestMapping(method = RequestMethod.GET, path = "/recommendations/{userId}")
    public ResponseEntity<List<Item>> getItemOnSale(@PathVariable int userId){
        List<Item> itemList = itemOnSaleService.getItemOnSaleWithUserId(userId);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

}
