package com.example.kiosk.customer.entity;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private Boolean deletedYn;

    @Column
    private LocalDateTime deletedDate;

    public void updateCustomer(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public void deleteCustomer() {
        this.deletedYn = true;
        this.deletedDate = LocalDateTime.now();
    }
}
