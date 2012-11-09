package co.edu.udea.ludens.enums;

import java.io.Serializable;
import java.util.HashMap;

public enum EnumElementType implements Serializable {

    FACTOR("Factor"),
    MATERIAL("Material"),
    POPULATION("Población");
    private static HashMap<String, EnumElementType> allEnums = null;
    private String type;

    private EnumElementType(String type) {
        this.setType(type);
    }

    public static EnumElementType getElementType(String typeName) {
        if (allEnums == null) {
            allEnums = new HashMap();
            EnumElementType[] types = EnumElementType.values();

            for (EnumElementType tp : types) {
                allEnums.put(tp.getType(), tp);
            }
        }

        return (allEnums.get(typeName));
    }

    public String getType() {

        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }
}