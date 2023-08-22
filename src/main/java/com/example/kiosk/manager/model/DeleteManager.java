package com.example.kiosk.manager.model;

import com.example.kiosk.manager.entity.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeleteManager {
    private Long id;
    private String email;
    private Boolean deletedYn;
    private LocalDateTime deletedDate;

    public static DeleteManager of(Manager manager) {
        return DeleteManager.builder()
                .id(manager.getId())
                .email(manager.getEmail())
                .deletedYn(manager.getDeletedYn())
                .deletedDate(manager.getDeletedDate())
                .build();
    }
}