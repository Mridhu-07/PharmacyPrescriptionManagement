package com.pharmacy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private String id;
    private Customer customer;
    private LocalDate validUntil;
    private List<PrescriptionItem> items;

    public Prescription(String id, Customer customer,
                        LocalDate validUntil) {
        this.id = id;
        this.customer = customer;
        this.validUntil = validUntil;
        this.items = new ArrayList<>();
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }

    public boolean isValid() {
        return !validUntil.isBefore(LocalDate.now());
    }

    public void addItem(PrescriptionItem item) {
        items.add(item);
    }

    public PrescriptionItem findItem(String medicineId) {
        for (PrescriptionItem item : items) {
            if (item.getMedicine().getId().equalsIgnoreCase(medicineId)) {
                return item;
            }
        }
        return null;
    }
}
