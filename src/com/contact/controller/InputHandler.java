package com.contact.controller;

import com.contact.entity.Contact;
import com.contact.entity.PhoneBook;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    private final PhoneBook phoneBook;

    public InputHandler(Scanner scanner, PhoneBook phoneBook) {
        this.scanner = scanner;
        this.phoneBook = phoneBook;
    }

    public void proceed() throws IOException {
        while (true) {
            String action = messagePrintAndInput("[menu] Enter action (add, list, search, count, exit):");
            switch (action) {
                case "add":
                    processAddAction();
                    phoneBook.updateFile();
                    break;
                case "list":
                    processListAction();
                    break;
                case "search":
                    processSearchAction();
                    break;
                case "count":
                    processCountAction();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println(("Action [" + action + "] is not supported"));
            }
            System.out.println();
        }
    }

    private void processAddAction() {
        String contactType = messagePrintAndInput("Enter the type (person, organization):");
        switch (contactType) {
            case "person" -> {
                String name = messagePrintAndInput("Enter the name:");
                String surname = messagePrintAndInput("Enter the surname:");
                String birthDate = messagePrintAndInput("Enter the birth date:");
                checkBirthDate(birthDate);
                String gender = messagePrintAndInput("Enter the gender (M, F):");
                checkGender(gender);
                String personNumber = messagePrintAndInput("Enter the number:");
                phoneBook.addPerson(name, surname, birthDate, gender, personNumber);
            }
            case "organization" -> {
                String organizationName = messagePrintAndInput("Enter the organization name:");
                String address = messagePrintAndInput("Enter the address:");
                String organizationNumber = messagePrintAndInput("Enter the number:");
                phoneBook.addOrganization(organizationName, address, organizationNumber);
            }
            default -> {
                System.out.println(("com.contact.entity.Contact type [" + contactType + "] is not supported"));
                return;
            }
        }

        System.out.println("The record added.");
    }

    private void processListAction() {
        List<Contact> all = phoneBook.getAll();

        if (all.isEmpty()) {
            System.out.println("The Phone Book is empty.");
            return;
        }
        showContactsNames(all);
        String listAction = messagePrintAndInput("\n[list] Enter action ([number], back):");
        try {
            int index = Integer.parseInt(listAction);
            Contact contact = all.get(index - 1);
            processRecordActions(contact);
        } catch (NumberFormatException | IOException exception) {
            if (!"back".equals(listAction)) {
                System.out.println(("List action [" + listAction + "] is not supported"));
            }
        }
    }

    private void processSearchAction() {
        List<Contact> all = phoneBook.getAll();

        if (all.isEmpty()) {
            System.out.println("The Phone Book is empty.");
            return;
        }

        while (true) {
            String query = messagePrintAndInput("Enter search query:");
            List<Contact> searchResult = phoneBook.getSearchResult(query);
            if (searchResult.size() == 1) {
                System.out.println("Found 1 result:");
            } else {
                System.out.printf("Found %d results:%n", searchResult.size());
            }
            showContactsNames(searchResult);
            String searchAction = messagePrintAndInput("\n[search] Enter action ([number], back, again):");
            try {
                int number = Integer.parseInt(searchAction);
                processRecordActions(searchResult.get(number - 1));
                return;
            } catch (NumberFormatException | IOException exception) {
                switch (searchAction) {
                    case "again":
                        continue;
                    case "back":
                        return;
                    default:
                        System.out.println(("Search action [" + searchAction + "] is not supported"));
                }
            }
        }
    }

    private void processCountAction() {
        System.out.printf("The Phone Book has %s records.%n", phoneBook.count());
    }

    private void processRecordActions(Contact contact) throws IOException {
        showContactInfo(contact);
        while (true) {
            String recordAction = messagePrintAndInput("\n[record] Enter action (edit, delete, menu):");
            switch (recordAction) {
                case "edit":
                    processEditAction(contact);
                    phoneBook.updateFile();
                    break;
                case "delete":
                    processDeleteAction(contact);
                    phoneBook.updateFile();
                    break;
                case "menu":
                    return;
                default:
                    System.out.println(("Record action [" + recordAction + "] is not supported"));
            }
        }
    }

    private void processEditAction(Contact contact) {
        String field = messagePrintfAndInput("Select a field (%s, number):%n", contact.getAllFieldsAsString());
        String value = messagePrintfAndInput("Enter %s:%n", field);

        contact.editField(field, value);
        System.out.println("Saved");
        showContactInfo(contact);
    }

    private void processDeleteAction(Contact contact) {
        phoneBook.remove(contact);
        System.out.println("The record removed!");
    }

    private void showContactsNames(List<Contact> contacts) {
        contacts.forEach(contact -> System.out.printf(
                "%d. %s%n",
                contacts.indexOf(contact) + 1,
                contact.getContactName())
        );
    }

    private void showContactInfo(Contact contact) {
        Map<String, String> fields = contact.getAllFieldsAsMap();
        fields.forEach((key, value) -> System.out.printf(
                "%s: %s%n",
                value,
                contact.getFieldValue(key)));
        String number = contact.hasNumber() ? contact.getPhoneNumber() : "[no number]";
        System.out.println("Number: " + number);
        System.out.println("Time created: " + contact.getCreateTimeDate());
        System.out.println("Time last edit: " + contact.getEditTimeDate());
    }

    private void checkBirthDate(String birthDate) {
        if (birthDate.isEmpty()) {
            System.out.println("Bad birth date!");
        }
    }

    private void checkGender(String gender) {
        if (gender.isEmpty()) {
            System.out.println("Bad gender!");
        }
    }

    private String messagePrintAndInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private String messagePrintfAndInput(String message, String args) {
        System.out.printf(message, args);
        return scanner.nextLine();
    }
}