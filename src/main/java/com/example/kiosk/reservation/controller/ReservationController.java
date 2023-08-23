package com.example.kiosk.reservation.controller;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.model.AddReservation;
import com.example.kiosk.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;


    // 예약 등록
    @PostMapping("/add")
    public ResponseEntity<?> addReservation(@RequestBody AddReservation.Request request) {
        Reservation reservation = reservationService.addReservation(request);
        return ResponseEntity.ok().body(AddReservation.Response.of(reservation));
    }
}
