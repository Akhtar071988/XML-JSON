package com.samixml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class App {

    public static void serializeToXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            List<String> otherContacts = Arrays.asList("Maria Anders", "Ana Trujillo", "Antonio Moreno");
            String xmlString = xmlMapper
                    .writeValueAsString(new AddressBook("Alfreds Futterkiste", "Maria Anders", "Sales Representative", "Obere Str. 57", "Berlin", "dummy@gmail.com", 12209, "Germany", 030-0074321, 030-0076545));
            // write to the console
            System.out.println(xmlString);

            // write XML string to file
            File xmlOutput = new File("serialized.xml");
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function deserializes the contents of an XML file into a Java Object
     * matching our PhoneDetails class.
     */
    public static void deserializeFromXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();

            // read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get("to_deserialize.xml")));

            // deserialize from the XML into a PhoneDetails object
            AddressBook deserializedData = xmlMapper.readValue(readContent, AddressBook.class);

            // Print object details
            System.out.println("Deserialized data: ");
            System.out.println("\tCompany Name: " + deserializedData.getCompanyName());
            System.out.println("\tContact Name: " + deserializedData.getContactName());
            System.out.println("\tContact Title: " + deserializedData.getContactTitle());
            System.out.println("\tAddress: " + deserializedData.getAddress());
            System.out.println("\tCity: " + deserializedData.getCity());
            System.out.println("\tEmail: " + deserializedData.getEmail());
            System.out.println("\tPostal Code: " + deserializedData.getPostalCode());
            System.out.println("\tCountry: " + deserializedData.getCountry());
            System.out.println("\tPhone: " + deserializedData.getPhone());
            System.out.println("\tFax: " + deserializedData.getFax());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Serializing to XML...\n");
        serializeToXML();

        System.out.println("\nDeserializing from XML...\n");
        deserializeFromXML();
    }
}

