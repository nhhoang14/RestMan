package com.RestMan.model;

import java.util.*;

public class CustomerStats6 extends Customer6 {
    private int bookingCount;
    private float totalRevenue;

    public CustomerStats6() {
    }

    public int getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(int bookingCount) {
        this.bookingCount = bookingCount;
    }

    public float getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
