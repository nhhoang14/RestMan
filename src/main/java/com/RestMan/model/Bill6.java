package com.RestMan.model;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill6 {
    private int id;
    private Date paymentTime;
    private String paymentMethod;
    private float discountAmount;
    private float totalAmount;
    private SalesStaff6 salesStaff;
    private DishOrdering6 dishOrdering;
}
