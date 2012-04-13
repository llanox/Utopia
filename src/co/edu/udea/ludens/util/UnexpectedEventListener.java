package co.edu.udea.ludens.util;

import co.edu.udea.ludens.domain.MessageEvent;

public interface UnexpectedEventListener extends LudensListener{	
	
	public void eventHappenning(MessageEvent event);

}
