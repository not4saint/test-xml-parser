package org.example.adapters;


import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String s) {
        return s.equals("1");
    }

    @Override
    public String marshal(Boolean c) {
        return c ? "1" : "0";
    }
}
