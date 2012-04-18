package co.edu.udea.ludens.enums;

public enum EnumEventType {
	
	FACTORS_PRODUCTION("Producción Factor",0), 
	MATERIALS_PRODUCTION("Producción Material",1), 
	POPULATION_PRODUCTION("Incremento Población",2), 
	REQUEST_TRADE("Solicitud de intercambio",3),
	INTERCHANGE("Intercambio comercial",4),
	INITIAL_SETUP("Inventario Inicial",5),
	UPGRADE_LEVEL("Subir nivel",6),
	LOG_INTO_SYSTEM("Ingresar al juego",7),
	LOG_OUT_SYSTEM("Salir del juego",8),
	;
	
	private String name;
	private int code;
	
	EnumEventType(String name, int code){
		this.name = name;
		this.code = code;
	}
	
	public String toString(){		
		return name;
	}
	
}
