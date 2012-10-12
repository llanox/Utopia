package co.edu.udea.ludens.enums;

import java.io.Serializable;

public enum EnumEventType implements Serializable {

    FACTORS_PRODUCTION("Producción Factor", 0),
    MATERIALS_PRODUCTION("Producción Material", 1),
    POPULATION_PRODUCTION("Incremento Población", 2),
    REQUEST_TRADE("Solicitud de intercambio", 3),
    INTERCHANGE("Intercambio comercial", 4),
    INITIAL_SETUP("Inventario Inicial", 5),
    UPGRADE_LEVEL("Subir nivel", 6),
    LOG_INTO_SYSTEM("Ingresar al juego", 7),
    LOG_OUT_SYSTEM("Salir del juego", 8),;
    private int code;
    private String name;

    EnumEventType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {

        return (this.name);
    }
}