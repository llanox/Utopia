package co.edu.udea.ludens.enums;

import java.io.Serializable;

public enum EnumEventType implements Serializable {

	FACTORS_PRODUCTION("Producción Factor", 0),
	MATERIALS_PRODUCTION("Producción Material", 1),
	POPULATION_PRODUCTION("Incremento Població", 2),
	REQUEST_TRADE("Solicitud de intercambio", 3),
	INTERCHANGE("Intercambio comercial", 4),
	INITIAL_SETUP("Inventario Inicial", 5),
	UPGRADE_LEVEL("Subir nivel", 6),
	LOG_INTO_SYSTEM("Ingresar al juego", 7),
	LOG_OUT_SYSTEM("Salir del juego", 8), ;
	private int code;
	private String name;

	EnumEventType(String name, int code) {
		this.setCode(code);
		this.setName(name);
	}

	public int getCode() {

		return (this.code);
	}

	private void setCode(int code) {
		this.code = code;
	}

	public String getName() {

		return (this.name);
	}

	private void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {

		return (this.name);
	}
}