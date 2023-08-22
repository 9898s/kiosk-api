package com.example.kiosk.manager.entity;

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
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean partnerYn;

    @Column
    private Boolean deletedYn;

    @Column
    private LocalDateTime deletedDate;

    public Manager(Long managerId) {
        this.id = managerId;
    }

    public void updateManager(String email, String password, Boolean partnerYn) {
        this.email = email;
        this.password = password;
        this.partnerYn = partnerYn;
    }

    public void deleteManager(Boolean deletedYn, LocalDateTime deletedDate) {
        this.deletedYn = deletedYn;
        this.deletedDate = deletedDate;
    }
}
