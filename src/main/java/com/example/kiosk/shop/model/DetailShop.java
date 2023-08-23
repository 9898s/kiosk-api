package com.example.kiosk.shop.model;

import com.example.kiosk.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetailShop {
    private Long id;
    private String name;
    private String location;
    private String description;

    public static DetailShop of(Shop shop) {
        return DetailShop.builder()
                .id(shop.getId())
                .name(shop.getName())
                .location(shop.getLocation())
                .description(shop.getDescription())
                .build();
    }
}
