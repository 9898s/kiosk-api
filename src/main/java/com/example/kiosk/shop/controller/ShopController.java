package com.example.kiosk.shop.controller;

import com.example.kiosk.review.entity.Review;
import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.model.*;
import com.example.kiosk.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchShop(@PathVariable String name) {
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

        return ResponseEntity.ok().body(SearchShopList.of(countShop, searchShopList));
    }

    // 매장 정보
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailShop(@PathVariable Long id) {
        Shop shop = shopService.detailShop(id);
        return ResponseEntity.ok().body(DetailShop.of(shop));
    }

    // 매장 리뷰
    @GetMapping("/review/{id}")
    public ResponseEntity<?> reviewListShop(@PathVariable Long id) {
        List<Review> reviews = shopService.reviewListShop(id);

        AtomicLong countReviews = new AtomicLong(0L);
        List<ReviewShop> reviewShopList = new ArrayList<>();

        reviews.forEach(e -> {
            ReviewShop reviewShop = ReviewShop.builder()
                    .customerEmail(e.getReservation().getCustomer().getEmail())
                    .contents(e.getContents())
                    .createdDate(e.getCreatedDate())
                    .build();

            reviewShopList.add(reviewShop);
            countReviews.getAndIncrement();
        });

        return ResponseEntity.ok().body(ReviewShopList.of(countReviews.get(), reviewShopList));
    }
}
