package com.example.kiosk.customer.model;

import com.example.kiosk.customer.entity.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class SignupCustomer {
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Request {
        @Email(message = "이메일 형식에 맞게 입력해주시길 바랍니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주시길 바랍니다.")
        private String password;

        @NotBlank(message = "휴대폰 번호를 입력해주시길 바랍니다.")
        private String phone;

        public Customer toEntity() {
            return Customer.builder()
                    .email(this.email)
                    .password(this.password)
                    .phone(this.phone)
                    .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private String email;
        private String password;
        private String phone;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;

        public static Response of(Customer customer) {
            return Response.builder()
                    .id(customer.getId())
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .phone(customer.getPhone())
                    .createdDate(customer.getCreatedDate())
                    .build();
        }
    }
}
