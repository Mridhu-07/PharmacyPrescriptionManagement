package com.pharmacy.service;

import java.time.LocalDate;
import java.util.*;

import com.pharmacy.model.*;
import com.pharmacy.exception.PharmacyException;

public class PharmacyService {
    private Map<String, Medicine> medicines = new LinkedHashMap<>();
    private Map<String, Customer> customers = new LinkedHashMap<>();
    private Map<String, Prescription> prescriptions = new LinkedHashMap<>();
    private List<Sale> sales = new ArrayList<>();
    private int saleCounter = 1;

    public void addMedicine(Medicine medicine) {
        if (medicines.containsKey(medicine.getId())) {
            throw new IllegalArgumentException("Medicine ID already exists.");
        }
        medicines.put(medicine.getId(), medicine);
    }

    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer ID already exists.");
        }
        customers.put(customer.getId(), customer);
    }

    public void addPrescription(Prescription prescription) {
        if (prescriptions.containsKey(prescription.getId())) {
            throw new IllegalArgumentException(
                "Prescription ID already exists.");
        }
        prescriptions.put(prescription.getId(), prescription);
    }

    public Medicine getMedicine(String id) {
        return medicines.get(id);
    }

    public Customer getCustomer(String id) {
        return customers.get(id);
    }

    public Prescription getPrescription(String id) {
        return prescriptions.get(id);
    }

    public void displayMedicines() {
        if (medicines.isEmpty()) {
            System.out.println("No medicines available.");
            return;
        }

        System.out.println("\n========== MEDICINE STOCK ==========");

        for (Medicine medicine : medicines.values()) {
            System.out.println(medicine);

            if (medicine.isExpired()) {
                System.out.println("  WARNING: MEDICINE EXPIRED!");
            } else if (!medicine.getExpiryDate().isAfter(
                    LocalDate.now().plusDays(30))) {
                System.out.println("  WARNING: EXPIRING WITHIN 30 DAYS!");
            }
        }
    }

    public Sale makeSale(String customerId, String pharmacistId,
                         String pharmacistName, String medicineId,
                         int quantity, String prescriptionId)
            throws PharmacyException {

        Customer customer = customers.get(customerId);
        Medicine medicine = medicines.get(medicineId);

        if (customer == null) {
            throw new PharmacyException("Customer not found.");
        }
        if (medicine == null) {
            throw new PharmacyException("Medicine not found.");
        }
        if (quantity <= 0) {
            throw new PharmacyException("Quantity must be positive.");
        }
        if (medicine.isExpired()) {
            throw new PharmacyException(
                "Sale rejected: medicine has expired.");
        }
        if (medicine.getStock() < quantity) {
            throw new PharmacyException("Insufficient stock.");
        }

        PrescriptionItem prescriptionItem = null;

        if (medicine.isPrescriptionRequired()) {
            Prescription prescription = prescriptions.get(prescriptionId);

            if (prescription == null) {
                throw new PharmacyException(
                    "Valid prescription is required.");
            }
            if (!prescription.isValid()) {
                throw new PharmacyException(
                    "Prescription has expired.");
            }
            if (!prescription.getCustomer().getId()
                    .equalsIgnoreCase(customerId)) {
                throw new PharmacyException(
                    "Prescription belongs to another customer.");
            }

            prescriptionItem = prescription.findItem(medicineId);

            if (prescriptionItem == null) {
                throw new PharmacyException(
                    "Medicine is not included in this prescription.");
            }
            if (prescriptionItem.getRemainingQuantity() < quantity) {
                throw new PharmacyException(
                    "Prescription quantity limit exceeded.");
            }
        }

        // Update stock and prescription only after all checks pass.
        if (prescriptionItem != null) {
            if (!prescriptionItem.useQuantity(quantity)) {
                throw new PharmacyException(
                    "Unable to use prescription quantity.");
            }
        }

        medicine.setStock(medicine.getStock() - quantity);

        Pharmacist pharmacist = new Pharmacist(
            pharmacistId, pharmacistName);

        Sale sale = new Sale("S" + saleCounter++, customer, pharmacist);
        sale.addDetail(new SaleDetail(
            medicine, quantity, prescriptionItem));
        sales.add(sale);

        return sale;
    }
}
