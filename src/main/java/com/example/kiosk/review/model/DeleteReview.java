package com.example.kiosk.review.model;

import com.example.kiosk.review.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeleteReview {
    private Long id;
    private Boolean deletedYn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedDate;

    public static DeleteReview of(Review review) {
        return DeleteReview.builder()
                .id(review.getId())
                .deletedYn(review.getDeletedYn())
                .deletedDate(review.getDeletedDate())
                .build();
    }
}
