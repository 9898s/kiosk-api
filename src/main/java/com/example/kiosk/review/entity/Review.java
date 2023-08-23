package com.example.kiosk.review.entity;

import com.example.kiosk.reservation.entity.Reservation;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column
    private Boolean deletedYn;

    @Column
    private LocalDateTime deletedDate;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void deleteReview() {
        this.deletedYn = true;
        this.deletedDate = LocalDateTime.now();
    }
}
