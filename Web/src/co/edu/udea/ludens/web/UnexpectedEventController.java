package co.edu.udea.ludens.web;

import com.icesoft.faces.async.render.SessionRenderer;

import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.util.UnexpectedEventListener;

public class UnexpectedEventController implements UnexpectedEventListener{

	
	private MessagesBean messagesBean;
	private UserSessionBean userSession;
	
	
	
	

	public void setMessagesBean(MessagesBean messagesBean) {
		this.messagesBean = messagesBean;
	}

	public MessagesBean getMessagesBean() {
		return messagesBean;
	}

	@Override
	public void eventHappenning(MessageEvent event) {
		
	 User player = event.getAffectedPlayer().getUser();
	 String loginName = player.getLogin();
	 String sessionLogin = userSession.getUser().getLogin();
	 
	 if(loginName.equalsIgnoreCase(sessionLogin)){
	         
			messagesBean.notifyMsg(event);			
			SessionRenderer.render(userSession.getUser().getLogin());

	 }		
		
	}

	public void setUserSession(UserSessionBean userSession) {
		this.userSession = userSession;
	}

	public UserSessionBean getUserSession() {
		return userSession;
	}




	
	
}
