package com.RestMan.model;

import java.util.*;

public class GoodsReceipt6 {
    private int id;
    private Date receiptDate;
    private WarehouseStaff6 warehouseStaff;
    private Supplier6 supplier;
    private List<GoodsReceiptDetail6> details;

    public GoodsReceipt6() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public WarehouseStaff6 getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff6 warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public Supplier6 getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier6 supplier) {
        this.supplier = supplier;
    }

    public List<GoodsReceiptDetail6> getDetails() {
        return details;
    }

    public void setDetails(List<GoodsReceiptDetail6> details) {
        this.details = details;
    }
}
