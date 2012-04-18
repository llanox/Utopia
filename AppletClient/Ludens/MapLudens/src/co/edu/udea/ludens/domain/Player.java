package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.List;







public class Player  implements Updateable {
	

    private long id;

	private Community community;
	private User user;
	private long startTime=0;
	private boolean producing;	
	private List<MessageEvent> events = new ArrayList<MessageEvent>();


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @generated
	 */
	public void setCommunity(Community community) {
		this.community = community;
	}

	/**
	 * @generated
	 */
	public Community getCommunity() {
		return community;
	}

	/**
	 * @generated
	 */
	public void setUser(User user) {
	
		this.user = user;
	}

	/**
	 * @generated
	 */
	public User getUser() {
	
		return user;
	}

	/**
	 * @generated
	 */
	public Player() {
	}




	


	public List<MessageEvent> getEvents() {
	
		return events;
	}

	public void setEvents(List<MessageEvent> events) {
	
		this.events = events;
	}

	

	public void setProducing(boolean producing) {

		this.producing = producing;
	}

	public boolean isProducing() {
	
		return producing;
	}

	public void setStartTime(Long startTime) {
	
		this.startTime = startTime;
	}

	public Long getStartTime() {

		return startTime;
	}

	@Override
	public void updateWith(Object o) {
		Player player = (Player) o;
		
		this.community = player.community;
        this.user = player.user;
        this.startTime = player.startTime;
        this.producing = player.producing;
        this.events = player.events;

		
	}




}
