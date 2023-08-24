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

public class UpdateCustomer {

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
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private String email;
        private String phone;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updatedDate;

        public static Response of(Customer customer) {
            return Response.builder()
                    .id(customer.getId())
                    .email(customer.getEmail())
                    .phone(customer.getPhone())
                    .updatedDate(customer.getUpdatedDate())
                    .build();
        }
    }
}
