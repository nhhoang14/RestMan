package com.RestMan.model;

import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsReceipt6 {
    private int id;
    private Date receiptDate;
    private float totalAmount;
    private WarehouseStaff6 warehouseStaff;
    private Supplier6 supplier;
    private List<GoodsReceiptDetail6> details;
}
