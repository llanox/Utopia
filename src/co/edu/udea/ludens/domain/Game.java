package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.udea.ludens.enums.EnumGameStatus;

@Entity
public class Game implements Updateable {
	
    @Id @GeneratedValue
    private Long id;
	private String name;
	private EnumGameStatus status = EnumGameStatus.NO_STARTED;;
	private long duration;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	private long productionTime;
	private long lowerThreshold;
	private long upperThreshold;



	
	@OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
	private List<Player> players = new ArrayList<Player>();

	@OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
	private List<UnexpectedEvent> unexpectedEvents = new ArrayList<UnexpectedEvent>();	
   
	@OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
	private List<Incrementable> defaultIncrementables = new ArrayList<Incrementable>();

	
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


	public void setUnexpectedEvents(List<UnexpectedEvent> unexpectedEvents) {
	
		this.unexpectedEvents = new ArrayList<UnexpectedEvent>(unexpectedEvents);
	}

	public List<UnexpectedEvent> getUnexpectedEvents() {

		return unexpectedEvents;
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
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
		this.defaultIncrementables  = game.defaultIncrementables;
	}
	
	public void setDefaultIncrementables(List<Incrementable> defaultIncrementables) {
		this.defaultIncrementables = defaultIncrementables;
	}

	
	public List<Incrementable> getDefaultIncrementables() {
		return defaultIncrementables;
	}

	public String toString(){
		
		return "id "+id+" name "+name+" duration "+duration+" startTime "+startTime+" endTime "+endTime+" productionTime "+productionTime+" lowerThreshold "+ lowerThreshold+ " upperThreshold "+upperThreshold+" status "+status;
	}


}
