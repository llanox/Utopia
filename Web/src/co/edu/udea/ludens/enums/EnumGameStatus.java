package co.edu.udea.ludens.enums;




public enum EnumGameStatus {
	STARTED("Inicializado"),
	FINISHED("Finalizado"), 
	NO_STARTED("No inicializado"),
	PAUSED("Pausado");
	
	private String status;

	private EnumGameStatus(String status) {
		this.setStatus(status);
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
}
