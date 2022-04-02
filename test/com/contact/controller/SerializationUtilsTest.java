package com.contact.controller;

import com.contact.entity.Contact;
import com.contact.entity.PhoneBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;

class SerializationUtilsTest {
    static String filePath = System.getProperty("user.dir") + "/src/com/contact/files/" + "phonebook.txt";
    static PhoneBook phoneBook = new PhoneBook(new String[]{});


    public int getLineCountByReader(String filePath) throws IOException {
        try (var lnr = new LineNumberReader(new BufferedReader(new FileReader(filePath)))) {
            while (lnr.readLine() != null) ;
            return lnr.getLineNumber();
        }
    }

    @BeforeAll
    static void init() {
        phoneBook.init();
        phoneBook.addOrganization("shop","123 street", "+7-921-333-33-33");
        phoneBook.addOrganization("Pet shop","321 street", "+7-921-555-55-55");
        phoneBook.addPerson("Mike", "Stanton", "21-03-1984", "M", "+7-921-111-11-11");
        phoneBook.addPerson("Nik", "Alexandr", "21-04-1984", "M", "+7-921-111-11-12");
    }

    @Test
    void serialize() throws IOException {
        SerializationUtils.serialize(phoneBook.getAll(), filePath);
        int actual = getLineCountByReader(filePath);
        Assertions.assertEquals(4, actual);
    }

    @Test
    void deserialize() throws IOException, ClassNotFoundException {
        for(Contact contact : phoneBook.getAll()) {
            phoneBook.remove(contact);
        }
        List<Contact> contacts = (List<Contact>) SerializationUtils.deserialize(filePath);
        for(Contact contact : contacts) {
            phoneBook.addContact(contact);
        }
        Assertions.assertEquals(4, phoneBook.getAll().size());
    }
}