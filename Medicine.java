package com.pharmacy.model;

import java.time.LocalDate;

public class Medicine {
    private String id;
    private String name;
    private double price;
    private int stock;
    private LocalDate expiryDate;
    private boolean prescriptionRequired;
    private boolean regulated;

    public Medicine(String id, String name, double price, int stock,
                    LocalDate expiryDate, boolean prescriptionRequired,
                    boolean regulated) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.expiryDate = expiryDate;
        this.prescriptionRequired = prescriptionRequired;
        this.regulated = regulated;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public boolean isPrescriptionRequired() {
        return prescriptionRequired;
    }
    public boolean isRegulated() { return regulated; }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isExpired() {
        return !expiryDate.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return id + " | " + name + " | Rs." + price
            + " | Stock: " + stock
            + " | Expiry: " + expiryDate
            + " | Prescription required: " + prescriptionRequired
            + " | Regulated: " + regulated;
    }
}