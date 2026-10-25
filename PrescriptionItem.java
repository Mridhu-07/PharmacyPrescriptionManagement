package com.pharmacy.model;

public class PrescriptionItem {
    private Medicine medicine;
    private int quantityAllowed;
    private int quantityUsed;

    public PrescriptionItem(Medicine medicine, int quantityAllowed) {
        this.medicine = medicine;
        this.quantityAllowed = quantityAllowed;
        this.quantityUsed = 0;
    }

    public Medicine getMedicine() { return medicine; }

    public int getRemainingQuantity() {
        return quantityAllowed - quantityUsed;
    }

    public boolean useQuantity(int quantity) {
        if (quantity <= 0 || quantity > getRemainingQuantity()) {
            return false;
        }
        quantityUsed += quantity;
        return true;
    }
}
