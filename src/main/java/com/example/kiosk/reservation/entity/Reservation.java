package com.example.kiosk.reservation.entity;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.global.domain.BaseEntity;
import com.example.kiosk.reservation.type.ReservationStatus;
import com.example.kiosk.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime reservationDate;

    @Column
    private LocalDateTime arrivedDate;

    @Column
    private Boolean arrivedYn;

    @Column
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void arriveCustomer() {
        this.arrivedDate = LocalDateTime.now();
        this.arrivedYn = true;
    }

    public void updateReservation(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void cancelReservation() {
        this.reservationStatus = ReservationStatus.CANCEL;
    }

    public void statusReservation(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
