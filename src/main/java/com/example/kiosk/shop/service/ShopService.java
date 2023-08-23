package com.example.kiosk.shop.service;

import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.entity.ShopRepository;
import com.example.kiosk.shop.model.AddShop;
import com.example.kiosk.shop.model.UpdateShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopRepository shopRepository;

    // 매장 등록
    @Transactional
    public Shop addShop(AddShop.Request request) {
        return shopRepository.save(request.toEntity());
    }

    // 매장 수정
    @Transactional
    public Shop updateShop(Long id, UpdateShop.Request request) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow();

        shop.updateShop(request.getManagerId(), request.getName(), request.getLocation(), request.getDescription());
        return shop;
    }

    // 매장 삭제
    @Transactional
    public void deleteShop(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow();

        shopRepository.delete(shop);
    }

    // 매장 검색 수
    public Long countSearchShop(String name) {
        return shopRepository.countAllByNameContains(name);
    }

    // 매장 검색
    @Transactional(readOnly = true)
    public List<Shop> searchShop(String name) {
        List<Shop> shopList = shopRepository.findAllByNameContains(name);
        return shopList;
    }

    public Shop detailShop(Long id) {
        return shopRepository.findById(id)
                .orElseThrow();
    }
}
