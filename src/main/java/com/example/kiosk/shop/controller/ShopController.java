package com.example.kiosk.shop.controller;

import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.model.*;
import com.example.kiosk.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/shop")
@RequiredArgsConstructor
@RestController
public class ShopController {
    private final ShopService shopService;

    // 매장 등록
    @PostMapping("/add")
    public ResponseEntity<?> addShop(@RequestBody @Valid AddShop.Request request) {
        Shop shop = shopService.addShop(request);
        return ResponseEntity.ok().body(AddShop.Response.of(shop));
    }

    // 매장 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShop(@PathVariable Long id, @RequestBody @Valid UpdateShop.Request request) {
        Shop shop = shopService.updateShop(id, request);
        return ResponseEntity.ok().body(UpdateShop.Response.of(shop));
    }

    // 매장 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
    }

    // 매장 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchShop(@RequestParam String name) {
        List<Shop> shopList = shopService.searchShop(name);

        long countShop = shopService.countSearchShop(name);
        List<SearchShop> searchShopList = new ArrayList<>();

        shopList.forEach(e -> {
            SearchShop searchShop = SearchShop.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .location(e.getLocation())
                    .build();

            searchShopList.add(searchShop);
        });

        return ResponseEntity.ok().body(SearchShopList.builder()
                .totalCount(countShop)
                .list(searchShopList)
                .build());
    }

    // 매장 정보
    @GetMapping("/detail")
    public ResponseEntity<?> detailShop(@RequestParam Long id) {
        Shop shop = shopService.detailShop(id);
        return ResponseEntity.ok().body(DetailShop.of(shop));
    }
}
