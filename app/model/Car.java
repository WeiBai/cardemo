package model;

import java.util.List;

public class Car{
    private String model;
    private String manufacturer;
    private List<Attribute> attributes;

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}