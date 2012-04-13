package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class Element implements Updateable  {
	
	
    @Id @GeneratedValue
    private Long id;
	private int calculatedValue;	
	private int level;
	private int quantity;
	private List<Integer> changeEvents = new ArrayList<Integer>();
	private Incrementable incrementable= new Incrementable();;
	private long actualUpgradingTime;
	




	public Element() {

	}


	private HashMap<String, List<IncrementableConstraint>> levelIncrements  = new HashMap<String, List<IncrementableConstraint>>();
	private HashMap<String, List<IncrementableConstraint>> levelConstraints = new HashMap<String, List<IncrementableConstraint>>();




	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setLevelIncrements(HashMap<String, List<IncrementableConstraint>> levelIncrements) {
	
		this.levelIncrements = levelIncrements;
	}

	
	public HashMap<String, List<IncrementableConstraint>> getLevelIncrements() {		
		
		return levelIncrements;
	}

	
	public void setLevelconstraints(HashMap<String, List<IncrementableConstraint>> levelConstraints) {
	
		this.levelConstraints = new HashMap<String, List<IncrementableConstraint>>(levelConstraints);
	}

	
	public HashMap<String, List<IncrementableConstraint>> getLevelConstraints() {
	
		return levelConstraints;
	}



	public void copy(Element that) {

		setLevelIncrements(new HashMap<String, List<IncrementableConstraint>>(that.getLevelIncrements()));
		setLevelconstraints(new HashMap<String, List<IncrementableConstraint>>(that.getLevelConstraints()));

	}







	public int getLevel() {
		return level;
	}


	public int getQuantity() {
		return quantity;
	}


	public int getLevelIncrementDelayRate() {
	
		return incrementable.getLevelIncrementDelayRate();
	}


	public int getProductionIncrementRate() {
		
		return incrementable.getProductionIncrementRate();
		
	}






	public int getCalculatedValue() {
		return calculatedValue;
	}





	public List<Integer> getChangeEvents() {
		return changeEvents;
	}


	public Incrementable getIncrementable() {
		return incrementable;
	}


	public void setLevel(int level) {
		
		this.level = level;
	}


	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}


	public void setLevelIncrementDelayRate(int levelIncrementDelayRate) {

		this.incrementable.setLevelIncrementDelayRate(levelIncrementDelayRate);
	}


	public void setProductionIncrementRate(int productionIncrementRate) {		
		
		this.incrementable.setProductionIncrementRate(productionIncrementRate);
	}


//	public void setInitialValue(int initialValue) {
//	
//		this.initialValue = initialValue;
//	}


	public void setInitialUpgradingTime(int initialUpgradingTime) {
		
		this.incrementable.setInitialUpgradingTime(initialUpgradingTime);
	}


	public void setCalculatedValue(int calculatedValue) {
		
		this.calculatedValue = calculatedValue;
	}





	public void setChangeEvents(List<Integer> changeEvents) {
	
		this.changeEvents = changeEvents;
	}


	public void setIncrementable(Incrementable incrementable) {
	
		this.incrementable = incrementable;
	}


	public void setLevelConstraints(HashMap<String, List<IncrementableConstraint>> levelConstraints) {
	
		this.levelConstraints = new HashMap<String, List<IncrementableConstraint>>(levelConstraints);
	}


	@Override
	public void updateWith(Object o) {
		Element newElement = (Element) o;
		this.calculatedValue = newElement.calculatedValue;
//	    this.initialValue = newElement.initialValue;
	    this.level = newElement.level;
	    this.quantity = newElement.quantity;
	    this.changeEvents = newElement.changeEvents;
	    this.incrementable = newElement.incrementable;
	    this.actualUpgradingTime = newElement.actualUpgradingTime;

	}


	/**
	 * @param actualUpgradingTime the actualUpgradingTime to set
	 */
	public void setActualUpgradingTime(long actualUpgradingTime) {
		this.actualUpgradingTime = actualUpgradingTime;
	}


	/**
	 * @return the actualUpgradingTime
	 */
	public long getActualUpgradingTime() {
		return actualUpgradingTime;
	}

}
