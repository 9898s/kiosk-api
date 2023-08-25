package com.example.kiosk.reservation.service;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.customer.entity.CustomerRepository;
import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.entity.ReservationRepository;
import com.example.kiosk.global.exception.ReservationException;
import com.example.kiosk.reservation.model.AddReservation;
import com.example.kiosk.reservation.model.StatusReservation;
import com.example.kiosk.reservation.model.UpdateReservation;
import com.example.kiosk.reservation.type.ReservationStatus;
import com.example.kiosk.shop.entity.Shop;
import com.example.kiosk.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
                .orElseThrow(() -> new ReservationException(NOT_FOUND_SHOP_ID));

        // 고객 아이디 번호 검사
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ReservationException(NOT_FOUND_CUSTOMER_ID));

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
        alreadyArrive(reservation);

        // 이미 취소 처리
        alreadyCancel(reservation);

        if (reservation.getReservationStatus() != ReservationStatus.OK) {
            throw new ReservationException(NOT_RESERVE_OK);
        }

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
        alreadyArrive(reservation);

        // 이미 취소 처리
        alreadyCancel(reservation);

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
        alreadyArrive(reservation);

        // 이미 취소 처리
        alreadyCancel(reservation);

        // 타임 아웃
        long minutes = getReserveMinutes(reservation.getReservationDate());
        if (minutes <= 10) {
            throw new ReservationException(NOT_CANCEL_TIME);
        }

        reservation.cancelReservation();
        return reservation;
    }

    // 예약 목록
    @Transactional(readOnly = true)
    public List<Reservation> listReservation(Long shopId) {
        // 매장 아이디 번호 검사
        if (shopRepository.findById(shopId).isEmpty()) {
            throw new ReservationException(NOT_FOUND_SHOP_ID);
        }

        return reservationRepository.findAllByShopIdOrderByReservationDateDesc(shopId);
    }

    // 예약 상태 수정
    @Transactional
    public Reservation statusReservation(StatusReservation.Request request) {
        Reservation reservation = getReservationId(request.getId());

        reservation.statusReservation(request.getReservationStatus());
        return reservation;
    }

    // 예약 아이디 번호
    private Reservation getReservationId(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException(NOT_FOUND_RESERVE_ID));
    }

    // 예약 시간 검사
    private long getReserveMinutes(LocalDateTime localDateTime) {
        Duration between = Duration.between(LocalDateTime.now(), localDateTime);
        return between.toMinutes();
    }

    // 이미 도착
    private void alreadyArrive(Reservation reservation) {
        if (Boolean.TRUE.equals(reservation.getArrivedYn())) {
            throw new ReservationException(ALREADY_ARRIVE_STATUS);
        }
    }

    // 이미 취소
    private void alreadyCancel(Reservation reservation) {
        if (reservation.getReservationStatus() == ReservationStatus.CANCEL) {
            throw new ReservationException(ALREADY_CANCEL_STATUS);
        }
    }
}
