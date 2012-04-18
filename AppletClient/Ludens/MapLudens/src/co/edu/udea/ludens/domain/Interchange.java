package co.edu.udea.ludens.domain;

import java.util.Date;



import co.edu.udea.ludens.enums.EnumTradeStatus;


public class Interchange implements Updateable{


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

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @generated
	 */
	public void setStartTime(Date startTime) {

		this.startTime = startTime;
	}

	/**
	 * @generated
	 */
	public Date getStartTime() {

		return this.startTime;
	}

	/**
	 * @generated
	 */
	public void setEndTime(Date endTime) {

		this.endTime = endTime;
	}

	/**
	 * @generated
	 */
	public Date getEndTime() {

		return this.endTime;
	}

	public void setPreviousInterchange(Interchange previousInterchange) {

		this.previousInterchange = previousInterchange;
	}

	public Interchange getPreviousInterchange() {

		return previousInterchange;
	}

	public EnumTradeStatus getStatus() {

		return status;
	}

	public void setStatus(EnumTradeStatus status) {

		this.status = status;
	}

	public void setSender(Player sender) {

		this.sender = sender;
	}

	public Player getSender() {

		return sender;
	}

	public void setReceiver(Player receiver) {

		this.receiver = receiver;
	}

	public Player getReceiver() {

		return receiver;
	}

	public Element getElFromReceiver() {

		return elFromReceiver;
	}

	public void setElFromReceiver(Element elFromReceiver) {

		this.elFromReceiver = elFromReceiver;
	}

	public Integer getQuantityFromSender() {

		return quantityFromSender;
	}

	public void setQuantityFromSender(Integer quantityFromSender) {

		this.quantityFromSender = quantityFromSender;
	}

	public Integer getQuantityFromReceiver() {

		return quantityFromReceiver;
	}

	public void setQuantityFromReceiver(Integer quantityFromReceiver) {

		this.quantityFromReceiver = quantityFromReceiver;
	}

	/**
	 * @generated
	 */
	public Interchange() {
	}

	public void setElFromSender(Element elFromSender) {

		this.elFromSender = elFromSender;
	}

	public Element getElFromSender() {

		return elFromSender;
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
