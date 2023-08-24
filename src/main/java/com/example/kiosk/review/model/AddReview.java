package com.example.kiosk.review.model;

import com.example.kiosk.reservation.entity.Reservation;
import com.example.kiosk.review.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AddReview {
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Request {
        @NotBlank(message = "리뷰 내용을 입력해주시길 바랍니다.")
        private String contents;

        private Long reservationId;

        public Review toEntity(Reservation reservation) {
            return Review.builder()
                    .contents(this.contents)
                    .reservation(reservation)
                    .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private String contents;
        private Long reservationId;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;

        public static Response of(Review review) {
            return Response.builder()
                    .id(review.getId())
                    .contents(review.getContents())
                    .reservationId(review.getReservation().getId())
                    .createdDate(review.getCreatedDate())
                    .build();
        }
    }
}
