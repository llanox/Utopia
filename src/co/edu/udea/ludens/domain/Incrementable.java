package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.util.ConstantsLudens;




@Entity
public class Incrementable implements Updateable {

    @Id @GeneratedValue
    private Long id;
   	private String name;
	private String description;	
	private String imageUrl = ConstantsLudens.NO_IMAGE_FILE;
	private EnumElementType type;
	private int levelIncrementDelayRate;
	private int productionIncrementRate;	
	private int initialValue;
	private long initialUpgradingTime;
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE}) 
	private Game game;

	@OneToMany(fetch=FetchType.EAGER,mappedBy="restrictedIncrementable",cascade = { CascadeType.MERGE}) 
	private List<IncrementableConstraint> constraints = new ArrayList<IncrementableConstraint>();
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Incrementable() {
		
	}



	@Override
	public Long getId() {
		return id;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getName() {
	
		return this.name;
	}

	public void setDescription(String description) {
		
		this.description = description;
	}

	public String getDescription() {

		return this.description;
	}

	public void setImageUrl(String imageUrl) {

		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {

		return this.imageUrl;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(EnumElementType type) {
	
		this.type = type;
	}


	/**
	 * @return the type
	 */
	public EnumElementType getType() {

		return type;
	}


	public int getLevelIncrementDelayRate() {
		return levelIncrementDelayRate;
	}

	public int getProductionIncrementRate() {
		return productionIncrementRate;
	}

	public long getInitialUpgradingTime() {
		return initialUpgradingTime;
	}



	public void setLevelIncrementDelayRate(int levelIncrementDelayRate) {

		this.levelIncrementDelayRate = levelIncrementDelayRate;
	}

	public void setProductionIncrementRate(int productionIncrementRate) {
	
		this.productionIncrementRate = productionIncrementRate;
	}

	public void setInitialUpgradingTime(long initialUpgradingTime) {
	
		this.initialUpgradingTime = initialUpgradingTime;
	}




	/**
	 * @param initialValue the initialValue to set
	 */
	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}

	/**
	 * @return the initialValue
	 */
	public int getInitialValue() {
		return initialValue;
	}



	/**
	 * @param constraints the constraints to set
	 */
	public void setConstraints(List<IncrementableConstraint> constraints) {
		this.constraints = constraints;
	}

	/**
	 * @return the constraints
	 */
	public List<IncrementableConstraint> getConstraints() {
		return constraints;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void updateWith(Object o) {
	
		Incrementable inc = (Incrementable) o;
		
		this.name = inc.name;
		this.description =inc.description;
		this.imageUrl = inc.imageUrl;
		this.type = inc.type;
		this.levelIncrementDelayRate = inc.levelIncrementDelayRate;
		this.productionIncrementRate = inc.productionIncrementRate;
		this.initialUpgradingTime = inc.initialUpgradingTime;
		this.initialValue = inc.initialValue;	
		this.constraints = inc.constraints;
		this.game = inc.game;

	
		
	}


}
