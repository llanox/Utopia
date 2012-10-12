package co.edu.udea.ludens.util;

import java.util.EventObject;
import java.util.List;

import co.edu.udea.ludens.enums.EnumEventType;
import co.edu.udea.ludens.web.ElementBean;

public class IncrementableEvent extends EventObject {
	private EnumEventType eventType;
	private List<ElementBean> incrementables;

	public IncrementableEvent(Object source, EnumEventType eventType,
			List<ElementBean> incrementables) {
		super(source);
		this.setEventType(eventType);
		this.setIncrementables(incrementables);
	}

	public void setEventType(EnumEventType eventType) {
		this.eventType = eventType;
	}

	public EnumEventType getEventType() {

		return (this.eventType);
	}

	public void setIncrementables(List<ElementBean> incrementables) {
		this.incrementables = incrementables;
	}

	public List<ElementBean> getIncrementables() {
	
		return (this.incrementables);
	}
}
