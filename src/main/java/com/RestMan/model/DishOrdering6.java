package com.RestMan.model;

import java.util.*;

public class DishOrdering6 {
    private int id;
    private Date orderDate;
    private float totalAmount;
    private String status;
    private TableBooking6 tableBooking;
    private List<DishOrderingDetail6> details;

    public DishOrdering6() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TableBooking6 getTableBooking() {
        return tableBooking;
    }

    public void setTableBooking(TableBooking6 tableBooking) {
        this.tableBooking = tableBooking;
    }

    public List<DishOrderingDetail6> getDetails() {
        return details;
    }

    public void setDetails(List<DishOrderingDetail6> details) {
        this.details = details;
    }
}
