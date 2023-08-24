package com.example.kiosk.manager.service;

import com.example.kiosk.manager.entity.Manager;
import com.example.kiosk.manager.entity.ManagerRepository;
import com.example.kiosk.manager.exception.ManagerException;
import com.example.kiosk.manager.model.SignupManager;
import com.example.kiosk.manager.model.UpdateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kiosk.global.type.ErrorCode.EXIST_ACCOUNT_EMAIL;
import static com.example.kiosk.global.type.ErrorCode.NOT_FOUND_ID;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    // 회원가입
    @Transactional
    public Manager signupManager(SignupManager.Request request) {
        existManagerEmail(request.getEmail());

        request.setPartnerYn(true);
        return managerRepository.save(request.toEntity());
    }

    // 정보수정
    @Transactional
    public Manager updateManager(Long id, UpdateManager.Request request) {
        Manager manager = getManagerId(id);
        existManagerEmail(request.getEmail());

        manager.updateManager(request.getEmail(), request.getPassword(), request.getPartnerYn());
        return manager;
    }

    // 계정삭제
    @Transactional
    public Manager deleteManager(Long id) {
        Manager manager = getManagerId(id);

        manager.deleteManager();
        return manager;
    }

    // 아아디를 찾을 수 없는 경우
    private Manager getManagerId(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new ManagerException(NOT_FOUND_ID));
    }

    // 이미 가입된 이메일이 있을 경우
    private void existManagerEmail(String email) {
        if (managerRepository.findByEmail(email).isPresent()) {
            throw new ManagerException(EXIST_ACCOUNT_EMAIL);
        }
    }
}
