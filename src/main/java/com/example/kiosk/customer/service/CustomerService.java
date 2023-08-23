package com.example.kiosk.customer.service;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.customer.entity.CustomerRepository;
import com.example.kiosk.customer.model.SignupCustomer;
import com.example.kiosk.customer.model.UpdateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    // 회원가입
    @Transactional
    public Customer signupCustomer(SignupCustomer.Request request) {
        return customerRepository.save(request.toEntity());
    }

    // 정보수정
    @Transactional
    public Customer updateCustomer(Long id, UpdateCustomer.Request request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow();

        customer.updateCustomer(request.getEmail(), request.getPassword(), request.getPhone());
        return customer;
    }

    // 계정삭제
    @Transactional
    public Customer deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow();

        customer.deleteCustomer(true, LocalDateTime.now());
        return customer;
    }
}
