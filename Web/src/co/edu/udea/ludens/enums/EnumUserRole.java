package co.edu.udea.ludens.enums;

public enum EnumUserRole {

	ADMIN("Administrador"), 
	PLAYER("Jugador");
	
	private String roleName;
	
	EnumUserRole(String roleName){
		this.setRoleName(roleName);		
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}


	
	
}
