package com.example.kiosk.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ListReservation {
    private Long totalCount;
    private List<ShopReservation> list;

    public static ListReservation of(long countReservation, List<ShopReservation> reservations) {
        return ListReservation.builder()
                .totalCount(countReservation)
                .list(reservations)
                .build();
    }
}
