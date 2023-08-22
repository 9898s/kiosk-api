package com.example.kiosk.manager.service;

import com.example.kiosk.manager.entity.Manager;
import com.example.kiosk.manager.entity.ManagerRepository;
import com.example.kiosk.manager.model.SignupManager;
import com.example.kiosk.manager.model.UpdateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    // 회원가입
    @Transactional
    public Manager signupManager(SignupManager.Request request) {
        request.setPartnerYn(true);
        return managerRepository.save(request.toEntity());
    }

    // 정보수정
    @Transactional
    public Manager updateManager(Long id, UpdateManager.Request request) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow();

        manager.updateManager(request.getEmail(), request.getPassword(), request.getPartnerYn());
        return manager;
    }

    // 계정삭제
    @Transactional
    public Manager deleteManager(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow();

        manager.deleteManager(true, LocalDateTime.now());
        return manager;
    }
}
