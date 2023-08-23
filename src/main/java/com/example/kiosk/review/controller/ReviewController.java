package com.example.kiosk.review.controller;

import com.example.kiosk.review.entity.Review;
import com.example.kiosk.review.model.AddReview;
import com.example.kiosk.review.model.DeleteReview;
import com.example.kiosk.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/review")
@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody @Valid AddReview.Request request) {
        Review review = reviewService.addReview(request, request.getReservationId());
        return ResponseEntity.ok().body(AddReview.Response.of(review));
    }

    // 리뷰 수정

    // 리뷰 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        Review review = reviewService.deleteReview(id);
        return ResponseEntity.ok().body(DeleteReview.of(review));
    }
}
