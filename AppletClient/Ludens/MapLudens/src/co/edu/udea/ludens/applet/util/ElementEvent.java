package co.edu.udea.ludens.applet.util;

import co.edu.udea.ludens.enums.EnumDataType;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

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

    public List<Object> getResults() {

        return (this.results);
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

    public String getMsg() {

        return (this.msg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EnumDataType getDataType() {

        return (this.dataType);
    }

    public void setEventType(EnumDataType dataType) {
        this.dataType = dataType;
    }
}