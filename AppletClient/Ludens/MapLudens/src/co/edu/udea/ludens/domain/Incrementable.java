package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import co.edu.udea.ludens.enums.EnumElementType;





public class Incrementable implements Updateable {

  
    private Long id;

	private String name;
	private String description;	
	private String imageUrl;
	private EnumElementType type;
	private int levelIncrementDelayRate;
	private int productionIncrementRate;	
	private int initialUpgradingTime;
	private int actualUpgradingTime;
	private String game;
	
	private List<GraphicIncrementable> levelImages = new ArrayList<GraphicIncrementable>();
	
	



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

	public Incrementable() {
		
		this.imageUrl ="sensation_data/food.png";
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


	




//
//	/**
//	 * @param levelImage the levelImage to set
//	 */
//	public void setLevelImage(HashMap<String,GraphicIncrementable> levelImage) {
//	
//		this.levelImage = new HashMap<String,GraphicIncrementable> (levelImage);
//	}
//
//	/**
//	 * @return the levelImage
//	 */
//	public HashMap<String,GraphicIncrementable> getLevelImage() {
//		return levelImage;
//	}

	public int getLevelIncrementDelayRate() {
		return levelIncrementDelayRate;
	}

	public int getProductionIncrementRate() {
		return productionIncrementRate;
	}

	public int getInitialUpgradingTime() {
		return initialUpgradingTime;
	}

	public int getActualUpgradingTime() {
		return actualUpgradingTime;
	}

	public void setLevelIncrementDelayRate(int levelIncrementDelayRate) {

		this.levelIncrementDelayRate = levelIncrementDelayRate;
	}

	public void setProductionIncrementRate(int productionIncrementRate) {
	
		this.productionIncrementRate = productionIncrementRate;
	}

	public void setInitialUpgradingTime(int initialUpgradingTime) {
	
		this.initialUpgradingTime = initialUpgradingTime;
	}

	public void setActualUpgradingTime(int actualUpgradingTime) {
	
		this.actualUpgradingTime = actualUpgradingTime;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(String game) {

		this.game = game;
	}

	/**
	 * @return the game
	 */
	public String getGame() {
		return game;
	}

	/**
	 * @param levelImages the levelImages to set
	 */
	public void setLevelImages(List<GraphicIncrementable> levelImages) {
		this.levelImages = levelImages;
	}

	/**
	 * @return the levelImages
	 */
	public List<GraphicIncrementable> getLevelImages() {
		return levelImages;
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
		this.actualUpgradingTime = inc.actualUpgradingTime;
		this.game = inc.game;
		this.levelImages = inc.levelImages;
	
		
	}




	




}
