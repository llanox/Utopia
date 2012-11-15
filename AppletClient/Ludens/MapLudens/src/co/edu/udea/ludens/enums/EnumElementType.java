package co.edu.udea.ludens.enums;

public enum EnumElementType {

    FACTOR("Factor"), MATERIAL("Material"), POPULATION("Población");
    private String type;

    private EnumElementType(String type) {
        this.setType(type);
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
}
