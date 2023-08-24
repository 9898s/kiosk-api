package com.example.kiosk.customer.service;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.customer.entity.CustomerRepository;
import com.example.kiosk.customer.exception.CustomerException;
import com.example.kiosk.customer.model.SignupCustomer;
import com.example.kiosk.customer.model.UpdateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kiosk.global.type.ErrorCode.EXIST_ACCOUNT_EMAIL;
import static com.example.kiosk.global.type.ErrorCode.NOT_FOUND_ID;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    // 회원가입
    @Transactional
    public Customer signupCustomer(SignupCustomer.Request request) {
        // 중복 이메일 체크
        existCustomerEmail(request.getEmail());

        return customerRepository.save(request.toEntity());
    }

    // 정보수정
    @Transactional
    public Customer updateCustomer(Long id, UpdateCustomer.Request request) {
        // 고객 아이디 번호
        Customer customer = getCustomerId(id);

        // 중복 이메일 체크
        existCustomerEmail(request.getEmail());

        customer.updateCustomer(request.getEmail(), request.getPassword(), request.getPhone());
        return customer;
    }

    // 계정삭제
    @Transactional
    public Customer deleteCustomer(Long id) {
        // 고객 아이디 번호
        Customer customer = getCustomerId(id);

        customer.deleteCustomer();
        return customer;
    }

    // 고객 아이디 번호
    private Customer getCustomerId(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException(NOT_FOUND_ID));
    }

    // 이메일 중복 검사
    private void existCustomerEmail(String email) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new CustomerException(EXIST_ACCOUNT_EMAIL);
        }
    }
}
