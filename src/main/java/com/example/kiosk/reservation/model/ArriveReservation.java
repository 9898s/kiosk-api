package com.example.kiosk.reservation.model;

import com.example.kiosk.reservation.entity.Reservation;
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
public class ArriveReservation {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivedDate;

    public static ArriveReservation of(Reservation reservation) {
        return ArriveReservation.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .arrivedDate(reservation.getArrivedDate())
                .build();
    }
}
