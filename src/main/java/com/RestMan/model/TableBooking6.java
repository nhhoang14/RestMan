package com.RestMan.model;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableBooking6 {
    private int id;
    private Date bookingDate;
    private Date appointmentDate;
    private int guestCount;
    private String note;
    private String status;
    private Customer6 customer;
    private Table6 table;
}
