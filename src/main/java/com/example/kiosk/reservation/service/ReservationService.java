package com.example.kiosk.reservation.service;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.customer.entity.CustomerRepository;
import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.entity.ReservationRepository;
import com.example.kiosk.reservation.model.AddReservation;
import com.example.kiosk.reservation.model.UpdateReservation;
import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ShopRepository shopRepository;
    private final CustomerRepository customerRepository;

    // 예약 등록
    @Transactional
    public Reservation addReservation(AddReservation.Request request) {
        Shop shop = shopRepository.findById(request.getShopId())
                .orElseThrow();

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow();

        return reservationRepository.save(request.toEntity(shop, customer));
    }

    // 예약 도착
    @Transactional
    public Reservation arriveReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();

        reservation.arriveCustomer(LocalDateTime.now(), true);
        return reservation;
    }

    // 예약 수정
    @Transactional
    public Reservation updateReservation(Long id, UpdateReservation.Request request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();

        reservation.updateReservation(request.getReservationDate());
        return reservation;
    }

    public Reservation cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();

        reservation.cancelReservation();
        return reservation;
    }
}
