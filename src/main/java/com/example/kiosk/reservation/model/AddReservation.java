package com.example.kiosk.reservation.model;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.type.ReservationStatus;
import com.example.kiosk.shop.entity.Shop;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class AddReservation {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Request {
        private Long shopId;
        private Long customerId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime reservationDate;

        public Reservation toEntity(Shop shop, Customer customer) {
            return Reservation.builder()
                    .shop(shop)
                    .customer(customer)
                    .reservationDate(this.reservationDate)
                    .arrivedYn(false)
                    .reservationStatus(ReservationStatus.NONE)
                    .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private Long shopId;
        private Long customerId;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime reservationDate;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;

        public static Response of(Reservation reservation) {
            return Response.builder()
                    .id(reservation.getId())
                    .shopId(reservation.getShop().getId())
                    .customerId(reservation.getCustomer().getId())
                    .reservationDate(reservation.getReservationDate())
                    .createdDate(reservation.getCreatedDate())
                    .build();
        }
    }
}