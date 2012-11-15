package co.edu.udea.ludens.enums;

import java.io.Serializable;

public enum EnumMsgs implements Serializable {

    ERROR_STARTING_SCHEDULER(EnumMsgType.ERROR, "Error iniciando el agendador de tareas", 1),
    CANT_UP_LEVEL(EnumMsgType.ERROR, "No puede subir %s al nivel %s pues aun no se ha definido este nivel", 2),
    DONT_MEET_LEVEL_CONSTRAINTS(EnumMsgType.ERROR, "El %s no es suficiente para subir al nivel %s ", 3),
    CANT_SCHEDULE_GAME(EnumMsgType.ERROR, "No se pudo iniciar la producciÃ³n periodica de materiales ", 4),
    PRODUCTION_PROCESS_HAPPENING(EnumMsgType.NOTICE, "Proceso de producciÃ³n de materiales en ejecuciÃ³n ...", 5),
    UPGRADING_ELEMENT_LEVEL(EnumMsgType.NOTICE, "Subiendo %s a un nivel superior, este proceso tardarÃ¡ en completarse %s segundos ", 6),
    SUCCESSFULY_UPGRADE(EnumMsgType.SUCCESS, "%s esta ahora en el nivel %s .", 7),
    INSUFFICIENT_RESOURCE(EnumMsgType.ERROR, " No hay suficiente %s para subir  %s al nivel %s  .", 8),
    NO_AVAILABILITY(EnumMsgType.ERROR, " No hay suficiente %s disponible para hacer el intercambio.", 9),
    ACCEPTED_OFFER(EnumMsgType.SUCCESS, "El jugador <strong> %s </strong> entregÃ³ %s unds de  %s   por %s unds de  %s  al jugador <strong> %s </strong> .", 10),
    DETAILS_NEW_OFFER(EnumMsgType.NOTICE, "El jugador <strong> %s </strong> quiere cambiar %s unds de  %s  por %s unds de  %s  .", 11),
    NEW_OFFER(EnumMsgType.NOTICE, "El jugador <strong> %s </strong>  ha publicado una nueva oferta de intercambio.", 12),
    CANCEL_OFFER(EnumMsgType.ERROR, "El jugador <strong> %s </strong>  ha cancelado la oferta de intercambio.", 13),
    NEGATIVE_UNEXPECTED_EVENT(EnumMsgType.ERROR, "%s. Se ha decrementado  de %s a %s ", 14),
    POSITIVE_UNEXPECTED_EVENT(EnumMsgType.ERROR, "%s. Se ha incrementado  de %s a %s ", 15),
    DELAY_UPGRADING_LEVEL(EnumMsgType.SUCCESS, "%s toma  %s segundos para subir de nivel %s a el %s.", 16),
    PRODUCING_ELEMENT_ERROR(EnumMsgType.ERROR, "No es posible subir de nivel %s pues hay un proceso de producciÃ³n vigente.", 17),
    PRODUCING_ELEMENT(EnumMsgType.SUCCESS, "La cantidad de %s ha incrementado despuÃ©s de producciÃ³n a %s unds.", 18),
    INITIAL_VALUE_ELEMENT(EnumMsgType.SUCCESS, "El valor inicial de %s es de %s unds.", 19),;
    private int code;
    private EnumMsgType msgType;
    private String customMsg;
    private String msg;

    EnumMsgs(EnumMsgType msgType, String msg, int code) {
        this.msg = msg;
        this.code = code;
        this.msgType = msgType;
    }

    public int getCode() {

        return (this.code);
    }

    public String getCustomMsg() {

        return (this.customMsg);
    }

    public String getMsg() {
        if (customMsg != null && !customMsg.isEmpty()) {
            msg = msg + " ." + customMsg;
        }

        return (this.msg);
    }

    public EnumMsgType getMsgType() {

        return (this.msgType);
    }

    public void setCustomMsg(String customMsg) {
        this.customMsg = customMsg;
    }

    @Override
    public String toString() {

        return ("[" + msgType + "--" + code + "]: " + getMsg());
    }
}