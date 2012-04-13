package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;

import com.icesoft.faces.async.render.SessionRenderer;

import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.util.MessageListener;

public class MessagesBean implements MessageListener {

	private List<MessageEvent> messages = new ArrayList<MessageEvent>();
	private List<MessageEvent> systemMessages = new ArrayList<MessageEvent>();
	
	@Override
	public void notifyMsg(MessageEvent event) {
		getMessages().add(0, event);	
		
	}
	
	
	public void setMessages(List<MessageEvent> messages) {
		this.messages = messages;
	}

	public List<MessageEvent> getMessages() {
		return messages;
	}


	/**
	 * @param systemMessages the systemMessages to set
	 */
	public void setSystemMessages(List<MessageEvent> systemMessages) {
		this.systemMessages = systemMessages;
	}


	/**
	 * @return the systemMessages
	 */
	public List<MessageEvent> getSystemMessages() {
		return systemMessages;
	}







}
