package com.example.kiosk.shop.entity;

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
public class Shop {
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

    public Shop(Long shopId) {
        this.id = shopId;
    }

    public void updateShop(Long managerId, String name, String location, String description) {
        this.manager = new Manager(managerId);
        this.name = name;
        this.location = location;
        this.description = description;
    }
}
