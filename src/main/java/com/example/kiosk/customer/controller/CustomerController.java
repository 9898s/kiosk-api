package com.example.kiosk.customer.controller;

import com.example.kiosk.customer.entity.Customer;
import com.example.kiosk.customer.model.DeleteCustomer;
import com.example.kiosk.customer.model.SignupCustomer;
import com.example.kiosk.customer.model.UpdateCustomer;
import com.example.kiosk.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/customer")
@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody @Valid SignupCustomer.Request request) {
        Customer customer = customerService.signupCustomer(request);
        return ResponseEntity.ok().body(SignupCustomer.Response.of(customer));
    }

    // 정보수정
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody @Valid UpdateCustomer.Request request) {
        Customer customer = customerService.updateCustomer(id, request);
        return ResponseEntity.ok().body(UpdateCustomer.Response.of(customer));
    }

    // 계정삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.deleteCustomer(id);
        return ResponseEntity.ok().body(DeleteCustomer.of(customer));
    }
}
