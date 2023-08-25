package com.example.kiosk.reservation.model;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShopReservation {
    private Long id;
    private String customerPhone;
    private LocalDateTime reservationDate;
    private LocalDateTime arrivedDate;
    private Boolean arrivedYn;
    private ReservationStatus reservationStatus;
}
