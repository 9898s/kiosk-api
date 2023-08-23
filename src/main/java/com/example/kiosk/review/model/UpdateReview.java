package com.example.kiosk.review.model;

import com.example.kiosk.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UpdateReview {
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Request {
        @NotBlank(message = "내용을 입력해주시길 바랍니다.")
        private String contents;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private String contents;

        public static Response of(Review review) {
            return Response.builder()
                    .id(review.getId())
                    .contents(review.getContents())
                    .build();
        }
    }
}
