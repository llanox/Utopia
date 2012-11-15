package co.edu.udea.ludens.enums;

import java.io.Serializable;

public enum EnumElementType implements Serializable {

    FACTOR("Factor"), MATERIAL("Material"), POPULATION("Población");
    private String type;

    private EnumElementType(String type) {
        this.setType(type);
    }

    public String getType() {

        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }
}