package com.test.vegstore.controller;

import com.test.vegstore.dto.HeaderDto;
import com.test.vegstore.exception.ApiRequestException;
import com.test.vegstore.service.FruitsService;
import com.test.vegstore.service.VegetablesService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsController {
    private final FruitsService fruitsService;
    private final VegetablesService vegetablesService;

    @GetMapping("/fruits")
    public ResponseEntity<JSONObject> selectFruits() throws Exception {
        return ResponseEntity.ok().body(fruitsService.selectFruits());
    }


    @GetMapping("/fruits/lists")
    public ResponseEntity<List<Object>> listFruits(@RequestHeader String Authorization) throws ApiRequestException {
        return ResponseEntity.ok().body(fruitsService.listFruits(Authorization));

    }

    @GetMapping("/fruits/products")
    public ResponseEntity<JSONObject> searchFruits(@RequestHeader String Authorization,
                                                   @RequestParam("name") String name) throws ApiRequestException {
        return ResponseEntity.ok().body(fruitsService.searchFruits(Authorization, name));
    }

    @GetMapping("/vegetables")
    public ResponseEntity<HeaderDto> selectVegetables() throws Exception {
        return ResponseEntity.ok().body(vegetablesService.selectVegetables());
    }


    @GetMapping("/vegetables/lists")
    public ResponseEntity<List<Object>> listVegetables(@RequestHeader String Authorization) throws ApiRequestException {
        return ResponseEntity.ok().body(vegetablesService.listVegetables(Authorization));

    }

    @GetMapping("/vegetables/products")
    public ResponseEntity<JSONObject> searchVegetables(@RequestHeader String Authorization,
                                                       @RequestParam("name") String name) throws ApiRequestException {
        return ResponseEntity.ok().body(vegetablesService.searchVegetables(Authorization, name));
    }
}
