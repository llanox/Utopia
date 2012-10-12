package com.llanox.chat.presentation;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.icesoft.faces.async.render.SessionRenderer;
import com.icesoft.faces.context.DisposableBean;
import com.llanox.chat.services.ChatManager;

public class ChatTalkerController implements DisposableBean {

	private Logger logger = Logger.getLogger(getClass());

	private ChatBean chatBean = new ChatBean();
	private boolean enabledChat = false;
	private boolean login = false;

	private String nickName;
	private String chatRoom;
	private String message;
	private ChatManager chatManager;

	public ChatTalkerController() {
		super();
	}

	public void register(ActionEvent ae) {
		login = false;
	}

	public void init(String chatRoom, String nickName) {
		this.chatRoom = chatRoom;
		this.nickName = nickName;
		chatBean = chatManager.getChatBeanForTalker(getChatRoom(), nickName);

		if (chatBean != null) {
			SessionRenderer.addCurrentSession(chatBean.getChatRoom());
		}

		logger.info("chatBean " + chatBean);
	}

	public void dispose() throws Exception {
		if (chatBean != null) {
			chatManager.closeChat(chatBean);
			SessionRenderer.removeCurrentSession(getChatRoom());
		}
		login = true;
	}

	public ChatBean getChatBean() {

		return (this.chatBean);
	}

	public void closeChat(ActionEvent event) {
		login = true;
	}

	public void setChatBean(ChatBean chatBean) {
		this.chatBean = chatBean;
	}

	public boolean isLogin() {
		if (chatBean == null) {
			login = true;
		}

		return login;
	}

	public void setChatManager(ChatManager chatManager) {
		this.chatManager = chatManager;
	}

	public ChatManager getChatManager() {

		return (this.chatManager);
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {

		return (this.nickName);
	}

	public String getMessage() {

		return (this.message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setEnabledChat(boolean enabledChat) {
		this.enabledChat = enabledChat;
	}

	public boolean isEnabledChat() {

		return (this.enabledChat);
	}

	// public void setUserSession(UserSessionBean userSession) {
	// this.userSession = userSession;
	// }
	//
	//
	// public UserSessionBean getUserSession() {
	// return userSession;
	// }

	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

	public String getChatRoom() {

		return (this.chatRoom);
	}
}
