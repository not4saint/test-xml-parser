package org.example.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.example.adapters.BooleanAdapter;
import org.example.adapters.LocalDateAdapter;


import java.time.LocalDate;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute(name = "OBJECTID")
    @XmlSchemaType(name = "long")
    private long id;

    @XmlAttribute(name = "NAME")
    private String name;

    @XmlAttribute(name = "TYPENAME")
    private String typeName;

    @XmlAttribute(name = "STARTDATE")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @XmlAttribute(name = "ENDDATE")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;

    @XmlAttribute(name = "ISACTUAL")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean isActual;

    @XmlAttribute(name = "ISACTIVE")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean isActive;
}
