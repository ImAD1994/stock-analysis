package com.jpmc.gbce.dto;

import java.math.BigDecimal;

public class VolumeWeightedStockPriceDTO {
    public VolumeWeightedStockPriceDTO(BigDecimal tradePriceWithQuantity, int quantity) {
        this.tradePriceWithQuantity = tradePriceWithQuantity;
        this.quantity = quantity;
    }

    public BigDecimal getTradePriceWithQuantity() {
        return tradePriceWithQuantity;
    }

    public void setTradePriceWithQuantity(BigDecimal tradePriceWithQuantity) {
        this.tradePriceWithQuantity = tradePriceWithQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private BigDecimal tradePriceWithQuantity;
    private int quantity;

}
