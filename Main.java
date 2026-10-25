package com.pharmacy.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.pharmacy.model.*;
import com.pharmacy.service.PharmacyService;
import com.pharmacy.exception.PharmacyException;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PharmacyService service = new PharmacyService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== PHARMACY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Medicine");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Prescription");
            System.out.println("4. Make Sale");
            System.out.println("5. Display Medicines");
            System.out.println("6. Exit");

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addMedicine();
                        break;
                    case 2:
                        addCustomer();
                        break;
                    case 3:
                        addPrescription();
                        break;
                    case 4:
                        makeSale();
                        break;
                    case 5:
                        service.displayMedicines();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Thank you for using the system.");
                        break;
                    default:
                        System.out.println("Invalid menu choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (PharmacyException e) {
                System.out.println("Sale error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static void addMedicine() {
        System.out.println("\n--- Add Medicine ---");

        System.out.print("Medicine ID: ");
        String id = readText();

        System.out.print("Medicine name: ");
        String name = readText();

        double price = readDouble("Price: ");
        while (price <= 0) {
            System.out.println("Price must be greater than zero.");
            price = readDouble("Price: ");
        }

        int stock = readInt("Stock quantity: ");
        while (stock < 0) {
            System.out.println("Stock cannot be negative.");
            stock = readInt("Stock quantity: ");
        }

        LocalDate expiry = readDate("Expiry date (yyyy-MM-dd): ");

        System.out.print("Prescription required? (yes/no): ");
        boolean prescriptionRequired = readYesNo();

        System.out.print("Is this a regulated medicine? (yes/no): ");
        boolean regulated = readYesNo();

        service.addMedicine(new Medicine(
            id, name, price, stock, expiry,
            prescriptionRequired, regulated));

        System.out.println("Medicine added successfully.");
    }

    private static void addCustomer() {
        System.out.println("\n--- Add Customer ---");

        System.out.print("Customer ID: ");
        String id = readText();

        System.out.print("Customer name: ");
        String name = readText();

        service.addCustomer(new Customer(id, name));
        System.out.println("Customer added successfully.");
    }

    private static void addPrescription() throws PharmacyException {
        System.out.println("\n--- Add Prescription ---");

        System.out.print("Prescription ID: ");
        String id = readText();

        System.out.print("Customer ID: ");
        String customerId = readText();

        Customer customer = service.getCustomer(customerId);

        if (customer == null) {
            throw new IllegalArgumentException(
                "Customer does not exist. Add the customer first.");
        }

        LocalDate validUntil = readDate(
            "Prescription valid until (yyyy-MM-dd): ");

        if (validUntil.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                "Prescription validity date cannot be in the past.");
        }

        System.out.print("Medicine ID on prescription: ");
        String medicineId = readText();

        Medicine medicine = service.getMedicine(medicineId);

        if (medicine == null) {
            throw new IllegalArgumentException(
                "Medicine does not exist.");
        }

        int allowedQuantity = readInt(
            "Prescribed quantity: ");

        while (allowedQuantity <= 0) {
            System.out.println("Quantity must be positive.");
            allowedQuantity = readInt("Prescribed quantity: ");
        }

        Prescription prescription =
            new Prescription(id, customer, validUntil);

        prescription.addItem(
            new PrescriptionItem(medicine, allowedQuantity));

        service.addPrescription(prescription);

        System.out.println("Prescription added successfully.");
    }

    private static void makeSale() throws PharmacyException {
        System.out.println("\n--- Make Sale ---");

        System.out.print("Customer ID: ");
        String customerId = readText();

        System.out.print("Pharmacist ID: ");
        String pharmacistId = readText();

        System.out.print("Pharmacist name: ");
        String pharmacistName = readText();

        System.out.print("Medicine ID: ");
        String medicineId = readText();

        int quantity = readInt("Quantity: ");

        System.out.print(
            "Prescription ID (enter NONE if not required): ");
        String prescriptionId = readText();

        if (prescriptionId.equalsIgnoreCase("NONE")) {
            prescriptionId = null;
        }

        Sale sale = service.makeSale(
            customerId, pharmacistId, pharmacistName,
            medicineId, quantity, prescriptionId);

        sale.printBill();
        System.out.println("Stock updated successfully.");
    }

    private static String readText() {
        while (true) {
            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.print("Input cannot be empty. Try again: ");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                double value = Double.parseDouble(sc.nextLine().trim());

                if (Double.isFinite(value)) {
                    return value;
                }

                System.out.println("Enter a finite number.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return LocalDate.parse(sc.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println(
                    "Invalid date. Use yyyy-MM-dd, e.g. 2027-12-31.");
            }
        }
    }

    private static boolean readYesNo() {
        while (true) {
            String answer = readText();

            if (answer.equalsIgnoreCase("yes")) {
                return true;
            }

            if (answer.equalsIgnoreCase("no")) {
                return false;
            }

            System.out.print("Please enter yes or no: ");
        }
    }
}
