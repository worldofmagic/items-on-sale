package com.rbc.itemsonsale.controller;

import com.rbc.itemsonsale.model.Item;
import com.rbc.itemsonsale.service.ItemOnSaleService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class ItemOnSaleControllerTest {
    @MockBean
    ItemOnSaleService itemOnSaleService;

    @InjectMocks
    @Autowired
    ItemOnSaleController itemOnSaleController;


    @Before
    public void setup(){
        Item item = new Item();
        item.setOnSale(true);
        item.setCategory("food");
        item.setItemName("apple");
        item.setId(1);
        List<Item> res = new ArrayList<>();
        res.add(item);

        Mockito.when(itemOnSaleService.getItemOnSaleWithUserId(Mockito.any())).thenReturn(res);
    }

    /**
     * Mock controller testing
     * inject the mocked itemOnSaleService into itemOnSaleController
     */
    @Test
    void getItemOnSale() {
        Assert.assertEquals(itemOnSaleController.getItemOnSale(1).getStatusCode().toString(),
                HttpStatus.OK.toString());
    }

    /**
     * MVC testing
     * 1. using RestTemplate to login with /authenticate endpoint and get the jwt token
     * 2. using jwt token to access /recommendations/1
     * @throws JSONException
     */
    @Test
    void getItemOnSale_MVC_200() throws JSONException {

        RestTemplate restTemplate = new RestTemplate();


        // build login requset
        HttpHeaders preFlightHeaders = new HttpHeaders();
        preFlightHeaders.add("Content-type", MediaType.APPLICATION_JSON_VALUE);

        JSONObject payload = new JSONObject();
        payload.put("username","user");
        payload.put("password", "password");

        HttpEntity preFlightEntity = new HttpEntity(payload.toString(),preFlightHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/authenticate",
                HttpMethod.POST,
                preFlightEntity,
                String.class
        );


        // get token from response body and build request for recommendation endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.add("Origin", "http://shopping.rbc.com");

        String token  =
                "Bearer " + response.getBody()
                                    .replace("{\"token\":\"", "").replace(
                        "\"}", "");

        headers.add("Authorization",
                token);
        HttpEntity httpEntity = new HttpEntity(headers);



        // access recommendation endpoint
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/recommendations/1",
                HttpMethod.GET,
                httpEntity,
                String.class
        );
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }
}
