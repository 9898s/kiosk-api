package com.example.kiosk.manager.service;

import com.example.kiosk.manager.entity.Manager;
import com.example.kiosk.manager.entity.ManagerRepository;
import com.example.kiosk.global.exception.ManagerException;
import com.example.kiosk.manager.model.SignupManager;
import com.example.kiosk.manager.model.UpdateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kiosk.global.type.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    // 회원가입
    @Transactional
    public Manager signupManager(SignupManager.Request request) {
        // 이메일 중복 검사
        existManagerEmail(request.getEmail());

        request.setPartnerYn(true);
        return managerRepository.save(request.toEntity());
    }

    // 정보수정
    @Transactional
    public Manager updateManager(Long id, UpdateManager.Request request) {
        // 매니저 아이디 번호
        Manager manager = getManagerId(id);

        // 이메일 중복 검사
        existManagerEmail(request.getEmail());

        manager.updateManager(request.getEmail(), request.getPassword(), request.getPartnerYn());
        return manager;
    }

    // 계정삭제
    @Transactional
    public Manager deleteManager(Long id) {
        // 매니저 아이디 번호
        Manager manager = getManagerId(id);

        // 계정 삭제 검사
        if (Boolean.TRUE.equals(manager.getDeletedYn())) {
            throw new ManagerException(ALREADY_DELETE_MANAGER);
        }

        manager.deleteManager();
        return manager;
    }

    // 매니저 아이디 번호
    private Manager getManagerId(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new ManagerException(NOT_FOUND_MANAGER_ID));
    }

    // 중복 이메일 검사
    private void existManagerEmail(String email) {
        if (managerRepository.findByEmail(email).isPresent()) {
            throw new ManagerException(EXIST_MANAGER_EMAIL);
        }
    }
}
