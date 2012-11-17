package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.enums.EnumTradeStatus;
import java.io.Serializable;
import java.util.Date;

public class Interchange implements Serializable, Updateable {

    private static final long serialVersionUID = 1739542383L;
    private Long id;
    private Date startTime;
    private Date endTime;
    private Element elFromSender = new Element();
    private Element elFromReceiver = new Element();
    private Integer quantityFromSender;
    private Integer quantityFromReceiver;
    private Player sender;
    private Player receiver;
    private EnumTradeStatus status;
    private Interchange previousInterchange;

    public Interchange() {
    }

    public Long getId() {

        return (this.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {

        return (this.startTime);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {

        return (this.endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Element getElFromSender() {

        return (this.elFromSender);
    }

    public void setElFromSender(Element elFromSender) {
        this.elFromSender = elFromSender;
    }

    public Element getElFromReceiver() {

        return (this.elFromReceiver);
    }

    public void setElFromReceiver(Element elFromReceiver) {
        this.elFromReceiver = elFromReceiver;
    }

    public Integer getQuantityFromSender() {

        return (this.quantityFromSender);
    }

    public void setQuantityFromSender(Integer quantityFromSender) {
        this.quantityFromSender = quantityFromSender;
    }

    public Integer getQuantityFromReceiver() {

        return (this.quantityFromReceiver);
    }

    public void setQuantityFromReceiver(Integer quantityFromReceiver) {
        this.quantityFromReceiver = quantityFromReceiver;
    }

    public Player getSender() {

        return (this.sender);
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getReceiver() {

        return (this.receiver);
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public EnumTradeStatus getStatus() {

        return (this.status);
    }

    public void setStatus(EnumTradeStatus status) {
        this.status = status;
    }

    public Interchange getPreviousInterchange() {

        return (this.previousInterchange);
    }

    public void setPreviousInterchange(Interchange previousInterchange) {
        this.previousInterchange = previousInterchange;
    }

    @Override
    public void updateWith(Object o) {
        Interchange inter = (Interchange) o;

        this.startTime = inter.startTime;
        this.endTime = inter.endTime;
        this.elFromSender = inter.elFromSender;
        this.elFromReceiver = inter.elFromReceiver;
        this.quantityFromReceiver = inter.quantityFromReceiver;
        this.quantityFromSender = inter.quantityFromSender;
        this.sender = inter.sender;
        this.receiver = inter.receiver;
        this.status = inter.status;
        this.previousInterchange = inter.previousInterchange;
    }
}