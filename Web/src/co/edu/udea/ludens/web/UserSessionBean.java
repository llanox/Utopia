package co.edu.udea.ludens.web;

import java.util.Date;

import co.edu.udea.ludens.domain.User;
public class UserSessionBean {

	private User user;
	private Date startTime;
	private String actualPage="login.xhtml";
	private String actualGame = "";
	private String actualTab ="";
	

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setActualPage(String actualPage) {
		this.actualPage = actualPage;
	}

	public String getActualPage() {
		return actualPage;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setActualGame(String actualGame) {
		this.actualGame = actualGame;
	}

	public String getActualGame() {
		return actualGame;
	}

	public void setActualTab(String actualTab) {
		this.actualTab = actualTab;
	}

	public String getActualTab() {
		return actualTab;
	}


	
	
	
	

}
