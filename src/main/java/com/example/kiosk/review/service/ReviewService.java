package com.example.kiosk.review.service;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.reservation.entity.ReservationRepository;
import com.example.kiosk.review.entity.Review;
import com.example.kiosk.review.entity.ReviewRepository;
import com.example.kiosk.review.exception.ReviewException;
import com.example.kiosk.review.model.AddReview;
import com.example.kiosk.review.model.UpdateReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kiosk.global.type.ErrorCode.NOT_FOUND_ID;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    // 리뷰 등록
    @Transactional
    public Review addReview(AddReview.Request request, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReviewException(NOT_FOUND_ID));

        return reviewRepository.save(request.toEntity(reservation));
    }

    // 리뷰 목록

    // 리뷰 수정
    @Transactional
    public Review updateReview(Long id, UpdateReview.Request request) {
        Review review = getReviewId(id);

        review.updateRevice(request.getContents());
        return review;
    }

    // 리뷰 삭제
    @Transactional
    public Review deleteReview(Long id) {
        Review review = getReviewId(id);

        review.deleteReview();
        return review;
    }

    private Review getReviewId(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewException(NOT_FOUND_ID));
    }
}
