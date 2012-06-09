package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Unique;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;




@Entity
public class Player  implements Updateable {
	
	@Id @GeneratedValue
    private long id;	
	
	@Unique
	@OneToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE}) 
	private Game game;
	
	private long startTime=0;
	private boolean producing;	
	private HashMap<String, Element> developmentFactors;
	private HashMap<String, Element> materials ;
	private Element population;

	

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
		
		developmentFactors = new HashMap<String,Element>();
		materials = new HashMap<String,Element>();
		
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
		
	
        this.user = player.user;
        this.startTime = player.startTime;
        this.producing = player.producing;
        this.events = player.events;

		
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param developmentFactors the developmentFactors to set
	 */
	public void setDevelopmentFactors(HashMap<String, Element> developmentFactors) {
		
		this.developmentFactors = developmentFactors;
	}



	/**
	 * @param materials the materials to set
	 */
	public void setMaterials(HashMap<String, Element> materials) {
		this.materials = materials;
	}

	/**
	 * @return the materials
	 */
	public HashMap<String, Element> getMaterials() {
		return materials;
	}


	/**
	 * @param population the population to set
	 */
	public void setPopulation(Element population) {
		this.population = population;
	}

	public HashMap<String, Element> getDevelopmentFactors() {

		return developmentFactors;
	}

	public Element getPopulation() {
		return population;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}





}
