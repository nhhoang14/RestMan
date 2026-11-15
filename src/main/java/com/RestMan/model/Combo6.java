package com.RestMan.model;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Combo6 extends MenuItem6 {
    private Date startDate;
    private Date endDate;
    private List<ComboDetail6> comboDetails;
}
