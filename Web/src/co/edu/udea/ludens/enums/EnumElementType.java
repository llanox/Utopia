package co.edu.udea.ludens.enums;

import java.util.HashMap;

public enum EnumElementType {

	FACTOR("Factor"),
	MATERIAL("Material"),
	POPULATION("Población");
	
	private String type;
	private static HashMap<String,EnumElementType> allEnums =null;

	private EnumElementType(String type) {
		this.setType(type);
	}

	
	
	public static EnumElementType getElementType(String typeName){
		
		if(allEnums==null){
			allEnums = new HashMap<String,EnumElementType>();
			EnumElementType[] types= EnumElementType.values();
			
			for(EnumElementType tp:types){
				allEnums.put(tp.getType(), tp);
			}
			
		}
		
		return allEnums.get(typeName);
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
