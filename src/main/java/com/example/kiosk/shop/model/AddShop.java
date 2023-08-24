package com.example.kiosk.shop.model;

import com.example.kiosk.manager.entity.Manager;
import com.example.kiosk.shop.entity.Shop;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AddShop {

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

        public Shop toEntity(Manager manager) {
            return Shop.builder()
                    .manager(manager)
                    .name(this.name)
                    .location(this.location)
                    .description(this.description)
                    .build();
        }
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

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;

        public static Response of(Shop shop) {
            return Response.builder()
                    .id(shop.getId())
                    .managerId(shop.getManager().getId())
                    .name(shop.getName())
                    .location(shop.getLocation())
                    .description(shop.getDescription())
                    .createdDate(shop.getCreatedDate())
                    .build();
        }
    }
}
