package com.example.kiosk.shop.entity;

import com.example.kiosk.global.domain.BaseEntity;
import com.example.kiosk.manager.entity.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Column
    private String name;

    @Column
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    public void updateShop(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }
}
