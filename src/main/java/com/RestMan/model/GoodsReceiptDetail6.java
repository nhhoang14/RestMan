package com.RestMan.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsReceiptDetail6 {
    private int id;
    private int quantity;
    private float importPrice;
    private Ingredient6 ingredient;

    public float getAmount() {
        return quantity * importPrice;
    }
}
