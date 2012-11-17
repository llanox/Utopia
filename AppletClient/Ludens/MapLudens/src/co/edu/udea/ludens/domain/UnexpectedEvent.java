package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.enums.EnumMetric;
import java.io.Serializable;
import java.util.Date;

public class UnexpectedEvent implements Serializable, Updateable {

    private static final long serialVersionUID = 1739544483L;
    private Long id;
    private EnumMetric metric;
    private Date happeningTime;
    private String elementName;
    private Integer quantity;
    private String message;
    private boolean goodEvent;

    public UnexpectedEvent() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {

        return (this.id);
    }

    public void setMetric(EnumMetric metric) {
        this.metric = metric;
    }

    public EnumMetric getMetric() {

        return (this.metric);
    }

    public void setHappeningTime(Date happeningTime) {
        this.happeningTime = happeningTime;
    }

    public Date getHappeningTime() {

        return (this.happeningTime);
    }

    public void setGoodEvent(boolean goodEvent) {
        this.goodEvent = goodEvent;
    }

    public boolean isGoodEvent() {

        return (this.goodEvent);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {

        return (this.message);
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementName() {

        return (this.elementName);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {

        return (this.quantity);
    }

    @Override
    public void updateWith(Object o) {
        UnexpectedEvent event = (UnexpectedEvent) o;

        this.metric = event.metric;
        this.happeningTime = event.happeningTime;
        this.elementName = event.elementName;
        this.quantity = event.quantity;
        this.message = event.message;
        this.goodEvent = event.goodEvent;
    }
}