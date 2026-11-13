package com.RestMan.model;

import java.util.*;

public class Combo6 extends MenuItem6 {
    private Date startDate;
    private Date endDate;
    private List<ComboDetail6> comboDetails;

    public Combo6() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ComboDetail6> getComboDetails() {
        return comboDetails;
    }

    public void setComboDetails(List<ComboDetail6> comboDetails) {
        this.comboDetails = comboDetails;
    }
}
