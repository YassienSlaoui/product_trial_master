package com.product.trial.master.dto.type;

public enum InventoryStatus {

    INSTOCK("INSTOCK") , LOWSTOCK("LOWSTOCK") ,OUTOFSTOCK("OUTOFSTOCK");

    private String value;

    private InventoryStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
