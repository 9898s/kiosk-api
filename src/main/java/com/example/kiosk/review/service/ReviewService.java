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

import static com.example.kiosk.global.type.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    // 리뷰 등록
    @Transactional
    public Review addReview(AddReview.Request request, Long reservationId) {
        // 예약 아이디 번호 검사
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReviewException(NOT_FOUND_RESERVE_ID));

        // 예약 도착 확인
        if (Boolean.FALSE.equals(reservation.getArrivedYn())) {
            throw new ReviewException(NOT_ARRIVE_STATUS);
        }

        // 리뷰 중복 등록 검사
        if (reviewRepository.findByReservation(reservation).isPresent()) {
            throw new ReviewException(ALREADY_WRITE_REVIEW);
        }

        return reviewRepository.save(request.toEntity(reservation));
    }

    // 리뷰 수정
    @Transactional
    public Review updateReview(Long id, UpdateReview.Request request) {
        // 리뷰 아이디 번호
        Review review = getReviewId(id);

        review.updateRevice(request.getContents());
        return review;
    }

    // 리뷰 삭제
    @Transactional
    public Review deleteReview(Long id) {
        // 리뷰 아이디 번호
        Review review = getReviewId(id);

        if (Boolean.TRUE.equals(review.getDeletedYn())) {
            throw new ReviewException(ALREADY_DELETE_REVIEW);
        }

        review.deleteReview();
        return review;
    }

    // 리뷰 아이디 번호
    private Review getReviewId(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewException(NOT_FOUND_REVIEW_ID));
    }
}
