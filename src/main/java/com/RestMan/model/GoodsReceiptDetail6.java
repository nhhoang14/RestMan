package com.RestMan.model;

import java.util.*;

public class GoodsReceiptDetail6 {
    private int id;
    private int quantity;
    private float price;
    private Ingredient6 ingredient;

    public GoodsReceiptDetail6() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Ingredient6 getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient6 ingredient) {
        this.ingredient = ingredient;
    }
}
