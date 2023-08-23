package com.example.kiosk.customer.model;

import com.example.kiosk.customer.entity.Customer;
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
public class DeleteCustomer {
    private Long id;
    private String email;
    private Boolean deletedYn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedDate;

    public static DeleteCustomer of(Customer customer) {
        return DeleteCustomer.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .deletedYn(customer.getDeletedYn())
                .deletedDate(customer.getDeletedDate())
                .build();
    }
}
