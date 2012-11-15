/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ludens.applet.util;

import co.edu.udea.ludens.enums.EnumDataType;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 *
 * @author juanga
 */
public class ElementEvent extends EventObject {

    private List<Object> results = new ArrayList<Object>();
    private String msg;
    private EnumDataType dataType;

    public ElementEvent(Object source, List<Object> results, EnumDataType dataType, String msg) {
        super(source);
        this.results = results;
        this.msg = msg;
        this.dataType = dataType;
    }

    /**
     * @return the elements
     */
    public List<Object> getResults() {
        return results;
    }

    /**
     * @param elements the elements to set
     */
    public void setResults(List<Object> results) {
        this.results = results;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the eventType
     */
    public EnumDataType getDataType() {
        return dataType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(EnumDataType dataType) {
        this.dataType = dataType;
    }
}
