package org.example;

import jakarta.xml.bind.JAXBContext;
import org.example.models.Address;
import org.example.models.AddressHierarchy;
import org.example.models.AddressHierarchyList;
import org.example.models.AddressList;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XmlService {
    private AddressList addressList;

    //считываем дату и все id, после выводим адрес
    public void readDateAndIdThenPrint() {
        LocalDate date;
        long[] idArr;

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            date = LocalDate.parse(console.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            idArr = Arrays.stream(console.readLine().split(", ")).mapToLong(Long::parseLong).toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addressList = unmarshallAddressXml();

        for (long id : idArr) {
            for (Address address : addressList.getAddressLists()) {
                if (address.getId() == id && address.getStartDate().isBefore(date) && address.getEndDate().isAfter(date)) {
                    System.out.printf("%d: %s %s%n", id, address.getTypeName(), address.getName());
                }
            }
        }
    }

    //перебираем все конечные ноды адресов по их типу
    public void findByType(String typeName) {
        addressList = unmarshallAddressXml();

        for (Address address : addressList.getAddressLists()) {
            if (address.getTypeName().equals(typeName)) {
                findAllNodesAndPrintAddressTree(address.getId());
            }
        }
    }

    //для каждой ветки
    private void findAllNodesAndPrintAddressTree(long firstId) {
        AddressHierarchyList addressHierarchyList = unmarshallAddressHierarchyXml();
        List<Long> idList = new ArrayList<>();
        long id = firstId;

        //ищем все id каждой ноды полного адреса
        while (id != 0) {
            for (AddressHierarchy addressHierarchy : addressHierarchyList.getAddressLists()) {
                if (id == addressHierarchy.getId() && addressHierarchy.getIsActive()) {
                    idList.add(0, id);
                    id = addressHierarchy.getParentId();
                    break;
                }
            }
        }

        StringBuilder res = new StringBuilder();

        //по id получаем нужные данные и выводим в строку
        for (long curId : idList) {
            for (Address address : addressList.getAddressLists()) {
                if (address.getId() == curId && address.getIsActual() && address.getIsActive()) {
                    res.append(address.getTypeName()).append(" ").append(address.getName()).append(", ");
                }
            }
        }
        res.delete(res.length() - 2, res.length() - 1);
        System.out.println(res);
    }

    private AddressList unmarshallAddressXml() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AddressList.class);
            return (AddressList) jaxbContext.createUnmarshaller().unmarshal(new File("/home/artem/Documents/myProjects/myProjects/test-xml-parser/src/main/resources/AS_ADDR_OBJ.xml"));
        } catch (jakarta.xml.bind.JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private AddressHierarchyList unmarshallAddressHierarchyXml() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AddressHierarchyList.class);
            return (AddressHierarchyList) jaxbContext.createUnmarshaller().unmarshal(new File("/home/artem/Documents/myProjects/myProjects/test-xml-parser/src/main/resources/AS_ADM_HIERARCHY.xml"));
        } catch (jakarta.xml.bind.JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
