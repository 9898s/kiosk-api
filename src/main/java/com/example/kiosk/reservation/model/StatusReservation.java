package com.example.kiosk.reservation.model;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StatusReservation {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Request {
        private Long id;
        private ReservationStatus reservationStatus;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private ReservationStatus reservationStatus;

        public static Response of(Reservation reservation) {
            return Response.builder()
                    .id(reservation.getId())
                    .reservationStatus(reservation.getReservationStatus())
                    .build();
        }
    }
}