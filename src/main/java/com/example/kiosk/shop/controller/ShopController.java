package com.example.kiosk.shop.controller;

import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.model.AddShop;
import com.example.kiosk.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/shop")
@RequiredArgsConstructor
@RestController
public class ShopController {
    private final ShopService shopService;

    // 매장 등록
    @PostMapping("/add")
    public ResponseEntity<?> addShop(@RequestBody AddShop.Request request) {
        Shop shop = shopService.addShop(request);
        return ResponseEntity.ok().body(AddShop.Response.of(shop));
    }

    // 매장 수정

    // 매장 삭제

    // 매장 목록

    // 매장 정보
}
