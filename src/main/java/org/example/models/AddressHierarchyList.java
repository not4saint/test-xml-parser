package org.example.models;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "ITEMS")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressHierarchyList {
    @XmlElement(name = "ITEM")
    List<AddressHierarchy> addressLists;
}
