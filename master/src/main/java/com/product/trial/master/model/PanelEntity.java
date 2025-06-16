package com.product.trial.master.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Table(schema = "panel")
@Entity
@Data
public class PanelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPanel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "panel", cascade = CascadeType.ALL)
    private List<UserProductEntity> userProducts = new ArrayList<>();
}
