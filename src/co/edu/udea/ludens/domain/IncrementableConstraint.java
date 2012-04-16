package co.edu.udea.ludens.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




@Entity
public class IncrementableConstraint implements Updateable {


	@Id @GeneratedValue
	private Long id;
	private Integer level;	
	private String elementName;
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE) 
	private Incrementable restrictedIncrementable;
	private int initialValue;
	private int incrementRate;
	private Integer quantity;

	
	

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
	public void setLevel(Integer level) {

		this.level = level;
	}

	/**
	 * @generated
	 */
	public Integer getLevel() {
	
		return this.level;
	}





	/**
	 * @generated
	 */
	public IncrementableConstraint() {
	}

	public void setElementName(String elementName) {
	
		this.elementName = elementName;
	}

	public String getElementName() {

		return elementName;
	}






	public void setQuantity(Integer quantity) {
	
		this.quantity = quantity;
	}

	public Integer getQuantity() {

		return quantity;
	}

	/**
	 * @param incrementable the incrementable to set
	 */
	public void setRestrictedIncrementable(Incrementable incrementable) {
		this.restrictedIncrementable = incrementable;
	}

	/**
	 * @return the incrementable
	 */
	public Incrementable getRestrictedIncrementable() {
		return restrictedIncrementable;
	}

	@Override
	public void updateWith(Object o) {
		IncrementableConstraint constraint = (IncrementableConstraint) o;
		this.elementName = constraint.elementName;
		this.level = constraint.level;
		this.quantity = constraint.quantity;
		this.restrictedIncrementable = constraint.restrictedIncrementable;

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

	public int getIncrementRate() {
		return incrementRate;
	}

	public void setIncrementRate(int incrementRate) {
		this.incrementRate = incrementRate;
	}




}
