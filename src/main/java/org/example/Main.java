package org.example;

public class Main {
    public static void main(String[] args) {
        XmlService xmlService = new XmlService();
        xmlService.readDateAndIdThenPrint();
        xmlService.findByType("проезд");
    }
}