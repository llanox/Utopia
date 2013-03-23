package co.edu.udea.ludens.enums;

import java.io.Serializable;

public enum EnumGameStatus implements Serializable {

	NO_STARTED("No inicializado"),
	STARTED("Inicializado"),     
    PAUSED("Pausado"),
    FINISHED("Finalizado");
	 
    private String status;

    private EnumGameStatus(String status) {
        this.setStatus(status);
    }

    public String getStatus() {

        return (this.status);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}