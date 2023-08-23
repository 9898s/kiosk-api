package com.example.kiosk.reservation.model;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.type.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CancelReservation {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;

    private ReservationStatus reservationStatus;

    public static CancelReservation of(Reservation reservation) {
        return CancelReservation.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .reservationStatus(reservation.getReservationStatus())
                .build();
    }
}
