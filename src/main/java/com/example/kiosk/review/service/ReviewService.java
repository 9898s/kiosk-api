package com.example.kiosk.review.service;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.entity.ReservationRepository;
import com.example.kiosk.review.entity.Review;
import com.example.kiosk.review.entity.ReviewRepository;
import com.example.kiosk.review.model.AddReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    // 리뷰 등록
    @Transactional
    public Review addReview(AddReview.Request request, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow();

        return reviewRepository.save(request.toEntity(reservation));
    }

    // 리뷰 삭제
    public Review deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow();

        review.deleteReview();
        return review;
    }
}
