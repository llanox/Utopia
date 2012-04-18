package co.edu.udea.ludens.domain;

public class PlayerStatus implements Comparable<PlayerStatus>{
	
	private String login;
	private int average;
	private boolean online;
	private int ranking;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		
		this.login = login;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	
	@Override
	public int compareTo(PlayerStatus o) {
		int result = o.getAverage()-this.average ;
		return result;
	}

}
