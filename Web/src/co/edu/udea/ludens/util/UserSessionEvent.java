package co.edu.udea.ludens.util;

import java.util.EventObject;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumEventType;

public class UserSessionEvent extends EventObject {

	private EnumEventType eventType;
	private User user;

	public UserSessionEvent(Object source) {
		super(source);
	}

	public UserSessionEvent(Object source, EnumEventType eventType) {
		super(source);
		this.eventType = eventType;
	}

	public EnumEventType getEventType() {

		return (this.eventType);
	}

	public void setEventType(EnumEventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {

		return (this.user);
	}
}
