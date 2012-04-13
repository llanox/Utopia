package co.edu.udea.ludens.services;

import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.util.MessageListener;

public interface MessagesCentralService extends MessageListener {
	
	
	public void notifyMsg(MessageEvent event);
	
	
	
    }
