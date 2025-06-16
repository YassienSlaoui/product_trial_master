package com.product.trial.master.dto;

import com.product.trial.master.dto.type.InventoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductDTO {
    @NotNull(message = "id is mandatory")
    private Integer id;
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

    private long createdAt;

    private long updatedAt;
}