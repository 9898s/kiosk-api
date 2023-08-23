package com.example.kiosk.review.service;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.entity.ReservationRepository;
import com.example.kiosk.review.entity.Review;
import com.example.kiosk.review.entity.ReviewRepository;
import com.example.kiosk.review.model.AddReview;
import com.example.kiosk.review.model.UpdateReview;
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

    // 리뷰 목록

    // 리뷰 수정
    @Transactional
    public Review updateReview(Long id, UpdateReview.Request request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow();

        review.updateRevice(request.getContents());
        return review;
    }

    // 리뷰 삭제
    @Transactional
    public Review deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow();

        review.deleteReview();
        return review;
    }
}
