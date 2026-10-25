# PharmacyPrescriptionManagement
Java console application for pharmacy and prescription management
# Pharmacy & Prescription Management System

## 1. Project Overview

The Pharmacy & Prescription Management System is a Java console-based application developed to manage medicines, customers, prescriptions, sales, stock levels, and expiry dates. It helps ensure that medicines are sold according to prescription and regulatory requirements.

## 2. Problem Statement

Create a Java console application to manage medicines, customers, prescriptions, sales, and regulatory checks.

## 3. Objectives

- Manage medicine and customer details.
- Record prescriptions and prescription items.
- Process medicine sales and generate bills.
- Verify prescriptions before selling medicines that require them.
- Check medicine stock before completing a sale.
- Update stock immediately after a successful sale.
- Track expiry dates and prevent the sale of expired medicines.
- Identify regulated medicines and record the pharmacist responsible for each sale.

## 4. Technologies Used

- **Programming Language:** Java
- **IDE:** Eclipse
- **Interface:** Console-based application
- **Date Handling:** Java LocalDate API

## 5. Main Features

1. **Add Medicine:** Store medicine details, price, stock, expiry date, and prescription requirements.
2. **Add Customer:** Register customer details.
3. **Add Prescription:** Record prescription information and its validity.
4. **Make Sale:** Validate medicine availability, expiry, and prescription requirements before processing a sale.
5. **Display Medicines:** Display available stock and expiry warnings.
6. **Generate Bill:** Display sale details, total amount, and regulated medicine flags.
7. **Stock Management:** Update medicine quantities after successful sales.

## 6. Class Structure

The application uses the following Java classes:

- `Medicine` – Stores medicine details and checks expiry.
- `Customer` – Stores customer information.
- `Prescription` – Maintains prescription information and validity.
- `PrescriptionItem` – Tracks medicines and permitted prescription quantities.
- `Sale` – Represents a sale and generates the bill.
- `SaleDetail` – Stores individual medicine sale details.
- `Pharmacist` – Stores pharmacist information.
- `PharmacyException` – Handles pharmacy-related validation errors.
- `PharmacyService` – Implements the main pharmacy business operations.
- `Main` – Displays the menu and handles user interaction.

## 7. Project Structure

```text
src/
└── com/
    └── pharmacy/
        ├── model/
        │   ├── Medicine.java
        │   ├── Customer.java
        │   ├── Prescription.java
        │   ├── PrescriptionItem.java
        │   ├── Sale.java
        │   ├── SaleDetail.java
        │   └── Pharmacist.java
        ├── exception/
        │   └── PharmacyException.java
        ├── service/
        │   └── PharmacyService.java
        └── main/
            └── Main.java
```

## 8. Requirements

- Java Development Kit (JDK)
- Eclipse IDE or another Java IDE

## 9. How to Run the Project

1. Install a compatible JDK and open Eclipse.
2. Create or import the Java project.
3. Ensure all source files are placed in their corresponding packages.
4. Open `Main.java` in the `com.pharmacy.main` package.
5. Run the program as a Java Application.
6. Use the console menu to select the required operation.

## 10. Sample Testing Scenarios

The following scenarios can be tested:

- Successful medicine sale and bill generation.
- Rejection of a sale with an invalid prescription.
- Sale of regulated medicine with prescription verification.
- Rejection of expired medicine.
- Display of expiry warnings.
- Verification of updated stock after a successful sale.

## 11. Limitations

- The application uses in-memory data storage.
- Data is reset when the program is restarted.
- The application is operated through the console.

## 12. Conclusion

The Pharmacy & Prescription Management System demonstrates the use of Java object-oriented programming, classes, packages, exception handling, date validation, and business rules to manage pharmacy operations. It provides a foundation for a more advanced system with database storage and a graphical user interface.

## 13. Student Details

- **Name:** MRIDHULA M
- **Register Number:** 717825I333
- **Course:** Java Programming Assignment II
- **Specification:** 8 – Pharmacy & Prescription Management
