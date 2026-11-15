package com.RestMan.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishOrderingDetail6 {
    private int id;
    private int quantity;
    private float price;
    private MenuItem6 menuItem;

    public float getAmount() {
        return quantity * price;
    }
}
