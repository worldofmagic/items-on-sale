package com.rbc.itemsonsale.controller;

import com.rbc.itemsonsale.model.Item;
import com.rbc.itemsonsale.service.ItemOnSaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemOnSaleController {

    private final ItemOnSaleService itemOnSaleService;

    /**
     * Autowire dependencis with constructor
     * @param itemOnSaleService
     */
    public ItemOnSaleController(ItemOnSaleService itemOnSaleService) {
        this.itemOnSaleService = itemOnSaleService;
    }


    @Operation(summary = "Find recommendated items", description = "Search by userid", tags =
            "List<Item>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Foos"),
            @ApiResponse( responseCode = "403", description = "User didn't login")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/recommendations/{userId}")
    public ResponseEntity<List<Item>> getItemOnSale(@PathVariable int userId){
        List<Item> itemList = itemOnSaleService.getItemOnSaleWithUserId(userId);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

}
