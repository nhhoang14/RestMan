package com.RestMan.model;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishOrdering6 {
    private int id;
    private Date orderDate;
    private float estimatedTotalAmount;
    private String status;
    private TableBooking6 tableBooking;
    private List<DishOrderingDetail6> details;
}
