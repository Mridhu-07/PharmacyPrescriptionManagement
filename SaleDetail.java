package com.pharmacy.model;

public class SaleDetail {
    private Medicine medicine;
    private int quantity;
    private double unitPrice;
    private PrescriptionItem prescriptionItem;

    public SaleDetail(Medicine medicine, int quantity,
                      PrescriptionItem prescriptionItem) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.unitPrice = medicine.getPrice();
        this.prescriptionItem = prescriptionItem;
    }

    public Medicine getMedicine() { return medicine; }
    public int getQuantity() { return quantity; }
    public PrescriptionItem getPrescriptionItem() {
        return prescriptionItem;
    }

    public double getSubtotal() {
        return quantity * unitPrice;
    }
}
