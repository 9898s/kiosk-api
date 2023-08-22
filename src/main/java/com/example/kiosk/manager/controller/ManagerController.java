package com.example.kiosk.manager.controller;

import com.example.kiosk.manager.entity.Manager;
import com.example.kiosk.manager.model.DeleteManager;
import com.example.kiosk.manager.model.SignupManager;
import com.example.kiosk.manager.model.UpdateManager;
import com.example.kiosk.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/manager")
@RequiredArgsConstructor
@RestController
public class ManagerController {
    private final ManagerService managerService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signupManager(@RequestBody SignupManager.Request request) {
        Manager manager = managerService.signupManager(request);
        return ResponseEntity.ok().body(SignupManager.Response.of(manager));
    }

    // 정보수정
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateManager(@PathVariable Long id, @RequestBody UpdateManager.Request request) {
        Manager manager = managerService.updateManager(id, request);
        return ResponseEntity.ok().body(UpdateManager.Response.of(manager));
    }

    // 계정삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long id) {
        Manager manager = managerService.deleteManager(id);
        return ResponseEntity.ok().body(DeleteManager.of(manager));
    }
}
