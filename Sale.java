package com.pharmacy.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private String id;
    private Customer customer;
    private Pharmacist pharmacist;
    private LocalDateTime dateTime;
    private List<SaleDetail> details;

    public Sale(String id, Customer customer, Pharmacist pharmacist) {
        this.id = id;
        this.customer = customer;
        this.pharmacist = pharmacist;
        this.dateTime = LocalDateTime.now();
        this.details = new ArrayList<>();
    }

    public void addDetail(SaleDetail detail) {
        details.add(detail);
    }

    public void printBill() {
        System.out.println("\n========== PHARMACY BILL ==========");
        System.out.println("Sale ID: " + id);
        System.out.println("Date: " + dateTime);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Pharmacist: " + pharmacist.getName());
        System.out.println("-----------------------------------");

        double total = 0;

        for (SaleDetail detail : details) {
            Medicine medicine = detail.getMedicine();
            System.out.println(medicine.getName()
                + " x " + detail.getQuantity()
                + " = Rs." + detail.getSubtotal());

            if (medicine.isRegulated()) {
                System.out.println("  [REGULATED MEDICINE]");
            }

            if (detail.getPrescriptionItem() != null) {
                System.out.println("  Prescription verified.");
            }

            total += detail.getSubtotal();
        }

        System.out.println("-----------------------------------");
        System.out.printf("TOTAL: Rs.%.2f%n", total);
        System.out.println("===================================");
    }
}
