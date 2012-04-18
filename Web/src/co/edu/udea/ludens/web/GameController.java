package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIData;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import common.Logger;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.util.UpdateableView;
import co.edu.udea.ludens.util.UtopiaUtil;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class GameController implements UpdateableView{

	
	private Game actualGame = new Game();
	private GameService gameService;
	private List<SelectItem> units;
	private List<SelectItem> configurables;
	private boolean editingGame = false;
	private boolean settingGame = false;

	private long productionTime = 0;
	private long duration = 0;
	private long lowerThreshold = 0;
	private long upperThreshold = 0;
	
	private long durationUnitDivider = 1000;
	private long productionUnitDivider = 1000;
	private long lowerThresholdUnitDivider = 1000;
	private long upperThresholdUnitDivider = 1000;

	private UIData gamesTable;
	private List<Game> games = new ArrayList<Game>();

	private static final String SEGS = "segs";
	private static final String MINS = "mins";
	private static final String HRS = "hrs";
	private static final String DAYS = "días";

	private String durationUnit = SEGS;
	private String productionTimeUnit = SEGS;
	private String thresholdEventsUnit = SEGS;

	private static final String FACTORES_LABEL = "Factores";
	private static final String RECURSOS_LABEL = "Materiales";
	private static final String POBLACION_LABEL = "Población";
	private static final String EVENTS_LABEL = "Eventos";
	
	private Logger logger = Logger.getLogger(GameController.class);

	private String[] unitsNames = { SEGS, MINS, HRS, DAYS };
	private String[] configurableThings = { FACTORES_LABEL, RECURSOS_LABEL,
			POBLACION_LABEL, EVENTS_LABEL };

	public List<SelectItem> getUnits() {
		units = UtopiaUtil.getItems(unitsNames);
		return units;
	}

	@PostConstruct
	public void loadData() {
		games = gameService.findAllGames();
		logger.info("Retreaving all games ...");
		for(Game game: games){
			logger.info("Game ..."+game.getName()+"  date "+game.getStartTime());
		}

	}

	public void newGame(javax.faces.event.ActionEvent event) {
		editingGame = true;
		actualGame = new Game();
		clearTemporalValues();
	}

	public void saveGame(javax.faces.event.ActionEvent event) {
		editingGame = false;
		long result = 0;

		result = getMilsec(durationUnit, duration);

		actualGame.setDuration(result);

		result = getMilsec(thresholdEventsUnit, lowerThreshold);

		actualGame.setLowerThreshold(result);

		result = getMilsec(thresholdEventsUnit, upperThreshold);

		actualGame.setUpperThreshold(result);

		result = getMilsec(productionTimeUnit, productionTime);

		actualGame.setProductionTime(result);
		


		gameService.save(actualGame);
		actualGame = new Game();
		games = gameService.findAllGames();

		clearTemporalValues();
	}

	private void clearTemporalValues() {
		productionTime = 0;
		upperThreshold = 0;
		lowerThreshold = 0;
		duration = 0;
	}

	private long getMilsec(String unit, long time) {
		long result = 0;

		if (unit == null || time <= 0)
			return 0;

		if (SEGS.equalsIgnoreCase(unit)) {
			result = TimeUnit.SECONDS.toMillis(time);
			return result;
		}

		if (MINS.equalsIgnoreCase(unit)) {
			result = TimeUnit.MINUTES.toMillis(time);
			return result;
		}

		if (HRS.equalsIgnoreCase(unit)) {
			result = TimeUnit.HOURS.toMillis(time);
			return result;
		}

		if (DAYS.equalsIgnoreCase(unit)) {
			result = TimeUnit.DAYS.toMillis(time);
			return result;
		}

		return 0;
	}



	public void settingUpGame(javax.faces.event.ActionEvent event) {

		settingGame = true;
		int selectedRowIndex = gamesTable.getRowIndex();
		actualGame = games.get(selectedRowIndex);
		logger.info("selected game "+actualGame.getName());
		transformToActualUnits();
		

	}
	
	
	public void settingUpPlayers(javax.faces.event.ActionEvent event){
		int selectedRowIndex = gamesTable.getRowIndex();
		actualGame = games.get(selectedRowIndex);
		logger.info("selected game "+actualGame.getName());
		
	}

	public void listGames(javax.faces.event.ActionEvent event) {
		settingGame = false;
		games = gameService.findAllGames();

	}

public void changeTimeUnit(ValueChangeEvent event){
     
	logger.info("UI Component Id "+event.getComponent().getId()+" new value "+event.getNewValue());
	String idComponent = event.getComponent().getId();
	String unit = event.getNewValue().toString();
	
	
	
	if(idComponent.startsWith("somGameDuration")){
		this.durationUnit = unit;
		
		
	}
	
	if(idComponent.startsWith("somProductionTime")){
		this.productionTimeUnit = unit;	
	
	}
	
	if(idComponent.startsWith("somEnventTime")){
		this.thresholdEventsUnit = unit;	
	}
	
	
	
	
	
	transformToActualUnits();
	
}


	private long calculateUnitDivider(String unit) {

		long result = 1;
		
		if (unit == null)
			return 1;

		if (SEGS.equalsIgnoreCase(unit)) {
			result = 1000;
			return result;
		}

		if (MINS.equalsIgnoreCase(unit)) {
			result = 1000*60;
			return result;
		}

		if (HRS.equalsIgnoreCase(unit)) {
			result = 1000*60*60;
			return result;
		}

		if (DAYS.equalsIgnoreCase(unit)) {
			result = 1000*60*60*24;
			return result;
		}
		
		
	return result;
}

	public void editGame(javax.faces.event.ActionEvent event) {
		editingGame = true;
		int selectedRowIndex = gamesTable.getRowIndex();
		actualGame = games.get(selectedRowIndex);

		transformToActualUnits();
		
	}

	private void transformToActualUnits() {
		
		durationUnitDivider = calculateUnitDivider(durationUnit);		
		productionUnitDivider = calculateUnitDivider(productionTimeUnit);		
		upperThresholdUnitDivider = lowerThresholdUnitDivider = calculateUnitDivider(thresholdEventsUnit);

		
		duration = actualGame.getDuration()/durationUnitDivider;
		lowerThreshold = actualGame.getLowerThreshold()/productionUnitDivider;
		upperThreshold = actualGame.getUpperThreshold()/upperThresholdUnitDivider;		
		productionTime = actualGame.getProductionTime()/upperThresholdUnitDivider;
	}

	public void deleteGame(javax.faces.event.ActionEvent event) {
		editingGame = false;
		int selectedRowIndex = gamesTable.getRowIndex();
		actualGame = games.get(selectedRowIndex);
		gameService.delete(actualGame);
		actualGame = new Game();
		games = gameService.findAllGames();

	}

	public void cancelEditing(javax.faces.event.ActionEvent event) {
		editingGame = false;
		actualGame = new Game();
	}

	/**
	 * @param actualGame
	 *            the actualGame to set
	 */
	public void setActualGame(Game actualGame) {
		this.actualGame = actualGame;
	}

	/**
	 * @return the actualGame
	 */
	public Game getActualGame() {
		return actualGame;
	}

	public String[] getUnitsNames() {
		return unitsNames;
	}

	public void setUnitsNames(String[] unitsNames) {
		this.unitsNames = unitsNames;
	}

	public void setUnits(List<SelectItem> units) {
		this.units = units;
	}

	/**
	 * @param editingGame
	 *            the editingGame to set
	 */
	public void setEditingGame(boolean editingGame) {
		this.editingGame = editingGame;
	}

	/**
	 * @return the editingGame
	 */
	public boolean isEditingGame() {
		return editingGame;
	}

	/**
	 * @param gamesTable
	 *            the gamesTable to set
	 */
	public void setGamesTable(UIData gamesTable) {
		this.gamesTable = gamesTable;
	}

	/**
	 * @return the gamesTable
	 */
	public UIData getGamesTable() {
		return gamesTable;
	}

	/**
	 * @param games
	 *            the games to set
	 */
	public void setGames(List<Game> games) {
		this.games = games;
	}

	/**
	 * @return the games
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * @param gameService
	 *            the gameService to set
	 */
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	/**
	 * @return the gameService
	 */
	public GameService getGameService() {
		return gameService;
	}

	/**
	 * @param settingGame
	 *            the settingGame to set
	 */
	public void setSettingGame(boolean settingGame) {
		this.settingGame = settingGame;
	}

	/**
	 * @return the settingGame
	 */
	public boolean isSettingGame() {
		return settingGame;
	}

	/**
	 * @param configurables
	 *            the configurables to set
	 */
	public void setConfigurables(List<SelectItem> configurables) {
		this.configurables = configurables;
	}

	/**
	 * @return the configurables
	 */
	public List<SelectItem> getConfigurables() {
		configurables = UtopiaUtil.getItems(configurableThings);
		return configurables;
	}

	/**
	 * @param productionTime
	 *            the productionTime to set
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
	 * @param duration
	 *            the duration to set
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
	 * @param durationUnit
	 *            the durationUnit to set
	 */
	public void setDurationUnit(String durationUnit) {
		this.durationUnit = durationUnit;
	}

	/**
	 * @return the durationUnit
	 */
	public String getDurationUnit() {
		return durationUnit;
	}

	/**
	 * @param productionTimeUnit
	 *            the productionTimeUnit to set
	 */
	public void setProductionTimeUnit(String productionTimeUnit) {
		this.productionTimeUnit = productionTimeUnit;
	}

	/**
	 * @return the productionTimeUnit
	 */
	public String getProductionTimeUnit() {
		return productionTimeUnit;
	}

	/**
	 * @param thresholdEventsUnit
	 *            the thresholdEventsUnit to set
	 */
	public void setThresholdEventsUnit(String thresholdEventsUnit) {
		this.thresholdEventsUnit = thresholdEventsUnit;
	}

	/**
	 * @return the thresholdEventsUnit
	 */
	public String getThresholdEventsUnit() {
		return thresholdEventsUnit;
	}

	/**
	 * @param lowerThreshold
	 *            the lowerThreshold to set
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
	 * @param upperThreshold
	 *            the upperThreshold to set
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

	public long getDurationUnitDivider() {
		return durationUnitDivider;
	}

	public long getProductionUnitDivider() {
		return productionUnitDivider;
	}

	public long getLowerThresholdUnitDivider() {
		return lowerThresholdUnitDivider;
	}

	public long getUpperThresholdUnitDivider() {
		return upperThresholdUnitDivider;
	}

	public void setDurationUnitDivider(long durationUnitDivider) {
		this.durationUnitDivider = durationUnitDivider;
	}

	public void setProductionUnitDivider(long productionUnitDivider) {
		this.productionUnitDivider = productionUnitDivider;
	}

	public void setLowerThresholdUnitDivider(long lowerThresholdUnitDivider) {
		this.lowerThresholdUnitDivider = lowerThresholdUnitDivider;
	}

	public void setUpperThresholdUnitDivider(long upperThresholdUnitDivider) {
		this.upperThresholdUnitDivider = upperThresholdUnitDivider;
	}



	@Override
	public void update() {
		logger.info("Updating gamecontroller");		
		this.loadData();
		
		
	}

}
