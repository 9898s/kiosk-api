package com.example.kiosk.reservation.controller;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.model.*;
import com.example.kiosk.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    // 예약 목록
    @GetMapping("/list/{shopId}")
    public ResponseEntity<?> listReservation(@PathVariable Long shopId) {
        List<Reservation> reservations = reservationService.listReservation(shopId);

        Long countListReservation = reservationService.countListReservation(shopId);
        List<ShopReservation> shopReservations = new ArrayList<>();

        reservations.forEach(e -> {
            ShopReservation shopReservation = ShopReservation.builder()
                    .id(e.getId())
                    .customerPhone(e.getCustomer().getPhone())
                    .reservationDate(e.getReservationDate())
                    .arrivedDate(e.getArrivedDate())
                    .arrivedYn(e.getArrivedYn())
                    .reservationStatus(e.getReservationStatus())
                    .build();

            shopReservations.add(shopReservation);
        });
        return ResponseEntity.ok().body(ListReservation.of(countListReservation, shopReservations));
    }
}
