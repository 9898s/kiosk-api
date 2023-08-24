package com.example.kiosk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
class KioskApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime startDate = LocalDateTime.of(2023, 8, 24, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 8, 24, 2, 0, 0);

        Duration between = Duration.between(startDate, endDate);
        long minutes = between.toMinutes();
        System.out.println(minutes); // 60
    }

}
