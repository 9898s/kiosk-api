package com.example.kiosk.shop.service;

import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.entity.ShopRepository;
import com.example.kiosk.shop.model.AddShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopRepository shopRepository;

    // 매장 등록
    public Shop addShop(AddShop.Request request) {
        return shopRepository.save(request.toEntity());
    }
}
