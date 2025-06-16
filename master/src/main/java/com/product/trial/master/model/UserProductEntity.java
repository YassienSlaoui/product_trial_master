package com.product.trial.master.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(schema = "userProduct")
@Entity
@Data
public class UserProductEntity {
    @Id
    @GeneratedValue
    private Long userProductId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Integer productQuantityRequested;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "panel_id")
    private PanelEntity panel;
}
