package co.edu.udea.ludens.web;

import java.util.Date;

import co.edu.udea.ludens.domain.User;


public class UserSessionBean {

	private User user;
	private Date startTime;
	private String actualPage = "login.xhtml";
	private String actualGame = "";
	private String actualTab = "";

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {

		return (this.startTime);
	}

	public void setActualPage(String actualPage) {
		this.actualPage = actualPage;
	}

	public String getActualPage() {

		return (this.actualPage);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {

		return (this.user);
	}

	public void setActualGame(String actualGame) {
		this.actualGame = actualGame;
	}

	public String getActualGame() {

		return (this.actualGame);
	}

	public void setActualTab(String actualTab) {
		this.actualTab = actualTab;
	}

	public String getActualTab() {

		return (this.actualTab);
	}
}
