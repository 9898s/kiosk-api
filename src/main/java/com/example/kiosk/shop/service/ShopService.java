package com.example.kiosk.shop.service;

import com.example.kiosk.manager.entity.Manager;
import com.example.kiosk.manager.entity.ManagerRepository;
import com.example.kiosk.manager.exception.ManagerException;
import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.entity.ShopRepository;
import com.example.kiosk.shop.exception.ShopException;
import com.example.kiosk.shop.model.AddShop;
import com.example.kiosk.shop.model.UpdateShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.kiosk.global.type.ErrorCode.NOT_FOUND_MANAGER_ID;
import static com.example.kiosk.global.type.ErrorCode.NOT_FOUND_SHOP_ID;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final ManagerRepository managerRepository;

    // 매장 등록
    @Transactional
    public Shop addShop(AddShop.Request request) {
        // 매니저 아이디 검사
        Manager manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new ManagerException(NOT_FOUND_MANAGER_ID));

        return shopRepository.save(request.toEntity(manager));
    }

    // 매장 수정
    @Transactional
    public Shop updateShop(Long id, UpdateShop.Request request) {
        // 매장 아이디 번호
        Shop shop = getShopId(id);

        shop.updateShop(request.getName(), request.getLocation(), request.getDescription());
        return shop;
    }

    // 매장 삭제
    @Transactional
    public void deleteShop(Long id) {
        // 매장 아이디 번호
        Shop shop = getShopId(id);

        shopRepository.delete(shop);
    }

    // 매장 검색 수
    @Transactional(readOnly = true)
    public Long countSearchShop(String name) {
        return shopRepository.countAllByNameContains(name);
    }

    // 매장 검색
    @Transactional(readOnly = true)
    public List<Shop> searchShop(String name) {
        return shopRepository.findAllByNameContains(name);
    }

    // 매장 정보
    @Transactional(readOnly = true)
    public Shop detailShop(Long id) {
        return getShopId(id);
    }

    // 매장 아이디 번호
    private Shop getShopId(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new ShopException(NOT_FOUND_SHOP_ID));
    }
}
