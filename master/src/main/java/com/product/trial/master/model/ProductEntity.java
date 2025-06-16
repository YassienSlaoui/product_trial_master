package com.product.trial.master.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.product.trial.master.dto.type.InventoryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "products")
@Entity
public class ProductEntity {
    @Id
    private Integer productId;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private int shellId;
    private InventoryStatus inventoryStatus;
    private double rating;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long createdAt;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long updatedAt;
}