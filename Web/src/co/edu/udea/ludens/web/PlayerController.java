package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIData;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import common.Logger;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.UserService;
import co.edu.udea.ludens.util.UpdateableView;


public class PlayerController implements UpdateableView{

	private boolean settingPlayers = false;
	private GameService gameService;
	private UIData playersTable;
	private UserService userService;
	private PlayerService playerService;
	private GameController gameController;
	private List<Player> selectedPlayers= new ArrayList<Player> ();
	private List<SelectItem> availableUsers;
	private String loginActualUser;
	private Logger logger = Logger.getLogger(getClass());	
	
	
	public void settingPlayers(javax.faces.event.ActionEvent event) {		
		gameController.settingUpPlayers(event);
		settingPlayers = true;		
		updatingUsersAndPlayers();
	}
	
    @PostConstruct
	private void updatingUsersAndPlayers() {
    	
    	logger.debug(" Updating Player List");
		List<User> availableUsersList = userService.findUserByRole(false,EnumUserRole.PLAYER);
		selectedPlayers = playerService.findAllPlayersByGameName(true,gameController.getActualGame().getName());
		logger.debug(" Updating Player List "+selectedPlayers.size());
	    fillAvailableUsers(availableUsersList);
	    
	}
	
	public void removePlayer(javax.faces.event.ActionEvent event) {		
				
		int selectedRowIndex = playersTable.getRowIndex();
		Player player = selectedPlayers.remove(selectedRowIndex);
		Game game = gameController.getActualGame();		
		gameService.removePlayer(game,player);	
	        
		updatingUsersAndPlayers();
	}
	
	public void listGames(javax.faces.event.ActionEvent event) {		
		gameController.listGames(event);
		settingPlayers = false;
	  
	}
	
	
	
	public void addPlayer(javax.faces.event.ActionEvent event) {		
		Player player = new Player();
						
		if(loginActualUser==null || loginActualUser.isEmpty())
			return;		
		
		Game game = gameController.getActualGame();
		User user = userService.findUser(loginActualUser);
		player.setUser(user);
		user.setParticipatingInGame(true);
	
		
		playerService.save(player);
		
		if(game.getPlayers()==null){
			game.setPlayers(new ArrayList<Player>());
		}
		
		game.getPlayers().add(player);
		
		gameService.save(game);
				
		selectedPlayers.add(player);
		updatingUsersAndPlayers();
	}


	
	private void fillAvailableUsers(List<User> availableUsersList) {
		availableUsers = new ArrayList<SelectItem>();
		
		for(User user : availableUsersList ){
			availableUsers.add(new SelectItem(user.getLogin(), user.getLogin()));			
		}
		
	}


	/**accesors and mutators**/
	
	
	/**
	 * @param settingPlayers the settingPlayers to set
	 */
	public void setSettingPlayers(boolean settingPlayers) {
		this.settingPlayers = settingPlayers;
	}

	/**
	 * @return the settingPlayers
	 */
	public boolean isSettingPlayers() {
		return settingPlayers;
	}

	public GameService getGameService() {
		return gameService;
	}

	public UserService getUserService() {
		return userService;
	}

	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}




	/**
	 * @param gameController the gameController to set
	 */
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}


	/**
	 * @return the gameController
	 */
	public GameController getGameController() {
		return gameController;
	}


	/**
	 * @param playersTable the playersTable to set
	 */
	public void setPlayersTable(UIData playersTable) {
		this.playersTable = playersTable;
	}



	/**
	 * @return the playersTable
	 */
	public UIData getPlayersTable() {
		return playersTable;
	}


	/**
	 * @param selectedPlayers the selectedPlayers to set
	 */
	public void setSelectedPlayers(List<Player> selectedPlayers) {
		this.selectedPlayers = selectedPlayers;
	}


	/**
	 * @return the selectedPlayers
	 */
	public List<Player> getSelectedPlayers() {
		return selectedPlayers;
	}

	/**
	 * @param availableUsers the availableUsers to set
	 */
	public void setAvailableUsers(List<SelectItem> availableUsers) {
		this.availableUsers = availableUsers;
	}


	/**
	 * @return the availableUsers
	 */
	public List<SelectItem> getAvailableUsers() {
		return availableUsers;
	}


	/**
	 * @param loginActualUser the loginActualUser to set
	 */
	public void setLoginActualUser(String loginActualUser) {
		this.loginActualUser = loginActualUser;
	}

	/**
	 * @return the loginActualUser
	 */
	public String getLoginActualUser() {
		return loginActualUser;
	}

	@Override
	public void update() {	
	
		updatingUsersAndPlayers();
	
	}
	
}
