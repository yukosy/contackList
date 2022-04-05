package com.contact.controller;

import java.io.*;
import java.util.Properties;

public class Config {
    FileInputStream fis;
    Properties property = new Properties();

    public Config() {
    }

    public String getValueFilePath(String fileName) {
        String filePath = null;
        try {
            fis = new FileInputStream(fileName);
            property.load(fis);
            filePath = property.getProperty("filePath");
        } catch (
                IOException e) {
            System.err.println("ERROR: file don't exist!");
        }
        return filePath;
    }
}

