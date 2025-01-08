import java.io.*;
import java.util.*;

class Contact implements Serializable {
    String name;
    String phoneNumber;
    String email;

    // Constructor for Contact class
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Override the toString method to display contact info
    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

public class ContactManagementSystem {
    private static final String FILE_NAME = "contacts.txt";  // File to store contacts
    private static List<Contact> contactList = new ArrayList<>(); // Use List<Contact> with generics

    public static void main(String[] args) {
        loadContactsFromFile();  // Load contacts from file at the start

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\nContact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    editContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContactsToFile();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);

        scanner.close();  // Close the scanner
    }

    // Method to add a contact
    private static void addContact(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            System.out.println("Name, Phone Number, and Email cannot be empty. Contact not added.");
            return;
        }

        contactList.add(new Contact(name, phone, email));  // Add new contact
        System.out.println("Contact added successfully.");
    }

    // Method to view all contacts
    private static void viewContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact contact : contactList) {
                System.out.println(contact);  // Print each contact's details
            }
        }
    }

    // Method to edit a contact
    private static void editContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to edit: ");
        String name = scanner.nextLine().trim();

        Contact contactToEdit = null;
        for (Contact contact : contactList) {
            if (contact.name.equals(name)) {  // Case-sensitive comparison
                contactToEdit = contact;
                break;
            }
        }

        if (contactToEdit != null) {
            System.out.print("Enter new Phone Number: ");
            String phone = scanner.nextLine().trim();
            System.out.print("Enter new Email Address: ");
            String email = scanner.nextLine().trim();

            if (phone.isEmpty() || email.isEmpty()) {
                System.out.println("Phone Number and Email cannot be empty. Contact not updated.");
                return;
            }

            contactToEdit.phoneNumber = phone;  // Update contact info
            contactToEdit.email = email;
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Method to delete a contact
    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine().trim();

        Contact contactToDelete = null;
        for (Contact contact : contactList) {
            if (contact.name.equals(name)) {  // Case-sensitive comparison
                contactToDelete = contact;
                break;
            }
        }

        if (contactToDelete != null) {
            contactList.remove(contactToDelete);  // Remove contact from list
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Method to load contacts from the file
    @SuppressWarnings("unchecked")
    private static void loadContactsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contactList = (List<Contact>) ois.readObject();  // Read contacts from file
        } catch (FileNotFoundException e) {
            // If file not found, just start with an empty list
            System.out.println("No previous contacts found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    // Method to save contacts to the file
    private static void saveContactsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contactList);  // Write contact list to file
            System.out.println("Contacts saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }
}