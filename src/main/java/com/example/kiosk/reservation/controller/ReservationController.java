package com.example.kiosk.reservation.controller;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.model.AddReservation;
import com.example.kiosk.reservation.model.ArriveReservation;
import com.example.kiosk.reservation.model.CancelReservation;
import com.example.kiosk.reservation.model.UpdateReservation;
import com.example.kiosk.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 예약 도착
    @GetMapping("/arrive/{id}")
    public ResponseEntity<?> arriveReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.arriveReservation(id);
        return ResponseEntity.ok().body(ArriveReservation.of(reservation));
    }

    // 예약 수정
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody UpdateReservation.Request request) {
        Reservation reservation = reservationService.updateReservation(id, request);
        return ResponseEntity.ok().body(UpdateReservation.Response.of(reservation));
    }

    // 예약 취소
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.cancelReservation(id);
        return ResponseEntity.ok().body(CancelReservation.of(reservation));
    }
}
