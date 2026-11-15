package com.RestMan.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStats6 extends Customer6 {
    private int bookingCount;
    private float totalRevenue;
}
