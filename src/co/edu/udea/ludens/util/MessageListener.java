package co.edu.udea.ludens.util;

import co.edu.udea.ludens.domain.MessageEvent;

public interface MessageListener extends LudensListener{
	
	public void notifyMsg(MessageEvent event);

}
