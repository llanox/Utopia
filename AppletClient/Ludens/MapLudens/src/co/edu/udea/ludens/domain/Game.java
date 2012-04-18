package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import co.edu.udea.ludens.enums.EnumGameStatus;


public class Game implements Updateable {
	
 
    private Long id;
	

	private String name;

	private long duration;

	private Date startTime;

	private Date endTime;

	private long productionTime;
	
	private long lowerThreshold;
	
	private long upperThreshold;

	private EnumGameStatus status;

	private HashMap<String,Player> players = new HashMap<String,Player>();
	
	private Set<Incrementable> defaultDevelopmentFactors = new HashSet<Incrementable>();
	
	private List<UnexpectedEvent> unexpectedEvents = new ArrayList<UnexpectedEvent>();	

	private Set<Incrementable> defaultMaterials = new HashSet<Incrementable>();	

	private Population defaultPopulation;
	
	
	
	

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @generated
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @generated
	 */
	public String getName() {
		
		return this.name;
	}




	/**
	 * @generated
	 */
	public void setStartTime(Date startTime) {

		this.startTime = startTime;
	}

	/**
	 * @generated
	 */
	public Date getStartTime() {
	
		return this.startTime;
	}

	/**
	 * @generated
	 */
	public void setEndTime(Date endTime) {

		this.endTime = endTime;
	}

	/**
	 * @generated
	 */
	public Date getEndTime() {

		return this.endTime;
	}




	/**
	 * @generated
	 */
	public void setStatus(EnumGameStatus status) {
	
		this.status = status;
	}

	/**
	 * @generated
	 */
	public EnumGameStatus getStatus() {
	
		return this.status;
	}

	/**
	 * @generated
	 */
	public void setPlayers(HashMap<String,Player> players) {

		this.players = new HashMap<String,Player>(players);
	}

	/**
	 * @generated
	 */
	public HashMap<String,Player> getPlayers() {
	
		return players;
	}

	/**
	 * @generated
	 */
	public Game() {
		
		status = EnumGameStatus.NO_STARTED;
	}




	public void setUnexpectedEvents(List<UnexpectedEvent> unexpectedEvents) {
	
		this.unexpectedEvents = new ArrayList<UnexpectedEvent>(unexpectedEvents);
	}

	public List<UnexpectedEvent> getUnexpectedEvents() {

		return unexpectedEvents;
	}

	public void setDefaultDevelopmentFactors(Set<Incrementable> defaultDevelopmentFactors) {
	
		this.defaultDevelopmentFactors = new HashSet<Incrementable> (defaultDevelopmentFactors);
	}

	public Set<Incrementable> getDefaultDevelopmentFactors() {

		return defaultDevelopmentFactors;
	}

	public void setDefaultMaterials(Set<Incrementable> defaultMaterials) {

		this.defaultMaterials = new HashSet<Incrementable>(defaultMaterials);
	}

	public Set<Incrementable> getDefaultMaterials() {

		return defaultMaterials;
	}

	public void setDefaultPopulation(Population defaultPopulation) {

		this.defaultPopulation = defaultPopulation;
	}

	public Population getDefaultPopulation() {
	
		return defaultPopulation;
	}



	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {

		this.duration = duration;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {

		return duration;
	}

	/**
	 * @param productionTime the productionTime to set
	 */
	public void setProductionTime(long productionTime) {

		this.productionTime = productionTime;
	}

	/**
	 * @return the productionTime
	 */
	public long getProductionTime() {

		return productionTime;
	}

	/**
	 * @param lowerThreshold the lowerThreshold to set
	 */
	public void setLowerThreshold(long lowerThreshold) {
	
		this.lowerThreshold = lowerThreshold;
	}

	/**
	 * @return the lowerThreshold
	 */
	public long getLowerThreshold() {

		return lowerThreshold;
	}

	/**
	 * @param upperThreshold the upperThreshold to set
	 */
	public void setUpperThreshold(long upperThreshold) {

		this.upperThreshold = upperThreshold;
	}

	/**
	 * @return the upperThreshold
	 */
	public long getUpperThreshold() {
	
		return upperThreshold;
	}

	@Override
	public void updateWith(Object o) {
		Game game = (Game) o;
		
		this.name = game.name;
		this.duration = game.duration;
		this.startTime = game.startTime;
		this.endTime = game.endTime;
		this.productionTime = game.productionTime;
		this.lowerThreshold = game.lowerThreshold;
		this.upperThreshold = game.upperThreshold;
		this.status = game.status;		
		this.players = game.players;
		this.unexpectedEvents = game.unexpectedEvents;
		this.defaultDevelopmentFactors = game.defaultDevelopmentFactors;
		this.defaultMaterials = game.defaultMaterials;
		this.defaultPopulation = game.defaultPopulation;
	
		
	}


}
