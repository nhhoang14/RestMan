package com.RestMan.model;

import java.util.*;

public class Bill6 {
    private int id;
    private Date paymentTime;
    private String paymentMethod;
    private float discountAmount;
    private float finalAmount;
    private SalesStaff6 salesStaff;
    private DishOrdering6 dishOrdering;

    public Bill6() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(float finalAmount) {
        this.finalAmount = finalAmount;
    }

    public SalesStaff6 getSalesStaff() {
        return salesStaff;
    }

    public void setSalesStaff(SalesStaff6 salesStaff) {
        this.salesStaff = salesStaff;
    }

    public DishOrdering6 getDishOrdering() {
        return dishOrdering;
    }

    public void setDishOrdering(DishOrdering6 dishOrdering) {
        this.dishOrdering = dishOrdering;
    }
}
