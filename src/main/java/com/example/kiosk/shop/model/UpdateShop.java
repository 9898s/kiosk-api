package com.example.kiosk.shop.model;

import com.example.kiosk.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UpdateShop {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Request {
        private Long managerId;

        @NotBlank(message = "매장명을 입력해주시길 바랍니다.")
        private String name;

        @NotBlank(message = "매장 위치를 입력해주시길 바랍니다.")
        private String location;

        @NotBlank(message = "매장 설명을 입력해주시길 바랍니다.")
        private String description;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private Long managerId;
        private String name;
        private String location;
        private String description;

        public static Response of(Shop shop) {
            return Response.builder()
                    .id(shop.getId())
                    .managerId(shop.getManager().getId())
                    .name(shop.getName())
                    .location(shop.getLocation())
                    .description(shop.getDescription())
                    .build();
        }
    }
}
