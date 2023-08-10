package org.example.models;


import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "ADDRESSOBJECTS")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressList {
    @XmlElement(name = "OBJECT")
    private List<Address> addressLists;
}
