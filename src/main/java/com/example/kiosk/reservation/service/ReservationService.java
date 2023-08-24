package com.example.kiosk.reservation.service;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.customer.entity.CustomerRepository;
import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.entity.ReservationRepository;
import com.example.kiosk.reservation.exception.ReservationException;
import com.example.kiosk.reservation.model.AddReservation;
import com.example.kiosk.reservation.model.UpdateReservation;
import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.example.kiosk.global.type.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ShopRepository shopRepository;
    private final CustomerRepository customerRepository;

    // 예약 등록
    @Transactional
    public Reservation addReservation(AddReservation.Request request) {
        // 매장 아이디 번호 검사
        Shop shop = shopRepository.findById(request.getShopId())
                .orElseThrow(() -> new ReservationException(NOT_FOUND_ID));

        // 고객 아이디 번호 검사
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ReservationException(NOT_FOUND_ID));

        // 타임 아웃
        long minutes = getReserveMinutes(request.getReservationDate());
        if (minutes <= 10) {
            throw new ReservationException(NOT_RESERVE_TIME);
        }

        return reservationRepository.save(request.toEntity(shop, customer));
    }

    // 예약 도착
    @Transactional
    public Reservation arriveReservation(Long id) {
        // 예약 아이디 번호
        Reservation reservation = getReservationId(id);

        // 이미 도착 처리

        // 타임 아웃
        long minutes = getReserveMinutes(reservation.getReservationDate());
        if (minutes <= 10) {
            throw new ReservationException(NOT_ARRIVE_TIME);
        }

        reservation.arriveCustomer();
        return reservation;
    }

    // 예약 수정
    @Transactional
    public Reservation updateReservation(Long id, UpdateReservation.Request request) {
        // 예약 아이디 번호
        Reservation reservation = getReservationId(id);

        // 이미 도착 처리

        // 타임 아웃
        long minutes = getReserveMinutes(request.getReservationDate());
        if (minutes <= 10) {
            throw new ReservationException(NOT_RESERVE_TIME);
        }

        reservation.updateReservation(request.getReservationDate());
        return reservation;
    }

    // 예약 취소
    @Transactional
    public Reservation cancelReservation(Long id) {
        // 예약 아이디 번호
        Reservation reservation = getReservationId(id);

        // 이미 도착 처리

        // 타임 아웃
        long minutes = getReserveMinutes(reservation.getReservationDate());
        if (minutes <= 10) {
            throw new ReservationException(NOT_CANCEL_TIME);
        }

        reservation.cancelReservation();
        return reservation;
    }

    // 예약 아이디 번호
    private Reservation getReservationId(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException(NOT_FOUND_ID));
    }

    // 예약 시간 검사
    private long getReserveMinutes(LocalDateTime localDateTime) {
        Duration between = Duration.between(LocalDateTime.now(), localDateTime);
        return between.toMinutes();
    }
}
