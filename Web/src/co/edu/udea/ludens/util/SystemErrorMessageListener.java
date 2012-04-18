package co.edu.udea.ludens.util;

import co.edu.udea.ludens.domain.MessageEvent;

public interface SystemErrorMessageListener {
	
	public void notifySystemError(MessageEvent event);

}
