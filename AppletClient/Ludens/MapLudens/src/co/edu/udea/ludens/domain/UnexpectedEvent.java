package co.edu.udea.ludens.domain;

import java.util.Date;



import co.edu.udea.ludens.enums.EnumMetric;



public class UnexpectedEvent implements Updateable {


    private Long id;
	private EnumMetric  metric;
	private Date happeningTime;
	private String elementName;
	private Integer quantity;
	private String message;
	private boolean goodEvent;
	
	
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}
	public void setMetric(EnumMetric metric) {

		this.metric = metric;
	}
	public EnumMetric getMetric() {
		return metric;
	}
	public void setHappeningTime(Date happeningTime) {
		
		this.happeningTime = happeningTime;
	}
	public Date getHappeningTime() {

		return happeningTime;
	}
	public void setGoodEvent(boolean goodEvent) {
		this.goodEvent = goodEvent;
	}
	public boolean isGoodEvent() {
	
		return goodEvent;
	}
	public void setMessage(String message) {

		this.message = message;
	}
	public String getMessage() {
	
		return message;
	}
	public void setElementName(String elementName) {

		this.elementName = elementName;
	}
	public String getElementName() {

		return elementName;
	}
	public void setQuantity(Integer quantity) {
	
		this.quantity = quantity;
	}
	public Integer getQuantity() {
		
		return quantity;
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
