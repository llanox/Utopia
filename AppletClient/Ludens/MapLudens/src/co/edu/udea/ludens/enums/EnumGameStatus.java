package co.edu.udea.ludens.enums;

import java.io.Serializable;

public enum EnumGameStatus implements Serializable {

    PLAYING("Activo"), FINISHED("Finalizado"), NO_STARTED("No inicializado");
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