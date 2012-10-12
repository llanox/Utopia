package co.edu.udea.ludens.enums;

import java.io.Serializable;

public enum EnumUserRole implements Serializable {

    ADMIN("Administrador"),
    PLAYER("Jugador");
    private String roleName;

    EnumUserRole(String roleName) {
        this.setRoleName(roleName);
    }

    public String getRoleName() {

        return (this.roleName);
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}