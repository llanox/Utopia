package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.GameContainerService;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.IncrementableService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.UserService;
import co.edu.udea.ludens.util.LudensListener;
import co.edu.udea.ludens.util.LudensUtilBean;
import co.edu.udea.ludens.util.UpdateableView;

import com.icesoft.faces.async.render.SessionRenderer;
import com.llanox.chat.presentation.ChatTalkerController;
import common.Logger;

public class AuthenticationController {

	private static final String BLANK = "";

	private static final String DIRECTOR_PAGE = "director";
	// Controllers

	private List<String> ludensListenersName = new ArrayList<String>();
	private List<LudensListener> ludensListeners = new ArrayList<LudensListener>();

	// Services from Spring
	private UserService userService;
	private GameService gameService;
	private PlayerService playerService;
	private IncrementableService incrementableService;
	private GameContainerService gameContainerService;

	private UserSessionBean userSession;
	private String usuario;
	private String password;
	private ChatTalkerController talkerChat;
	private Logger logger = Logger.getLogger(AuthenticationController.class);
	private String[] adminViewsControllers = { "playerController",
			"gameController", "incrementableController" };
	public static final String GAME_PATH = "./game/";
	public static final String DEFAULT_TAB_PLAYER = GAME_PATH + "mapView.xhtml";
	public static final String LOGIN_PAGE = "login";
	public static final String DEFAULT_TAB_ADMIN = GAME_PATH
			+ "gameSetUpView.xhtml";

	public static HashMap<EnumUserRole, String> roleUrlMap = initializeMap();

	private static HashMap<EnumUserRole, String> initializeMap() {
		HashMap<EnumUserRole, String> roleUrlMap = new HashMap<EnumUserRole, String>();
		roleUrlMap.put(EnumUserRole.ADMIN, "admin.xhtml");
		roleUrlMap.put(EnumUserRole.PLAYER, "board.xhtml");

		return roleUrlMap;
	}

	public AuthenticationController() {
	}

	@PostConstruct
	public void subscribingListeners() {
		for (String name : ludensListenersName) {
			if (name == null)
				continue;

			ludensListeners.add((LudensListener) LudensUtilBean.findBean(name));
		}
		ludensListenersName = null;
		logger.debug("ludens listeners " + ludensListeners.size());
	}

	public String validateAction() throws LudensException {

		/*
		 * Se analizará el resultado para determinar si el usuario no existe, o
		 * si existe es el password el erroneo*
		 */
		String resultado = null;
		try {
			resultado = validate(getUsuario(), getPassword());
		} catch (LudensException e) {
			logger.error("Error validando usuario", e);
		}

		if (DIRECTOR_PAGE.equalsIgnoreCase(resultado))
			activatingRole(getUsuario());

		setUsuario(BLANK);
		setPassword(BLANK);

		return resultado;
	}

	private void activatingRole(String usuario) throws LudensException {
		User user = userService.findUser(usuario);
		enablingUserSession(user);
		EnumUserRole role = user.getRole();

		if (EnumUserRole.PLAYER == role) {
			Game game = gameService.findByUserLogin(user.getLogin());

			if (game == null)
				return;

			userSession.setActualGame(game.getName());
			// List<Incrementable> incrementables =
			// incrementableService.getAllIncrementablesGame(game.getName());
			// game.setDefaultIncrementables(incrementables);
			//
			// List<Player> players =
			// playerService.findAllPlayersByGameName(true,game.getName());
			// game.setPlayers(players);
			//

			gameContainerService.startGame(game);
			gameService.save(game);
			gameContainerService
					.suscribeListeners(userSession, ludensListeners);

		}
		gameContainerService.logIn(user);
	}

	public String validate(String usuario, String password)
			throws LudensException {
		String page = null;
		User user = userService.validateUser(usuario, password);

		if (user == null) {
			throw new LudensException(
					"El nombre de usuario o el password es incorrecto");
		}

		enablingUserSession(user);
		page = DIRECTOR_PAGE;

		return page;
	}

	private void enablingUserSession(User user) {
		SessionRenderer.addCurrentSession(user.getLogin());
		EnumUserRole role = user.getRole();
		user.setOnline(true);

		if (EnumUserRole.ADMIN == role) {
			userSession.setActualTab(DEFAULT_TAB_ADMIN);
			logger.info("Admin page");
		}

		if (EnumUserRole.PLAYER == role) {
			// TODO revisar que pasa con el chat
			userSession.setActualTab(DEFAULT_TAB_PLAYER);
			// talkerChat.init(userSession.getActualGame(),userSession.getUser().getLogin());

			logger.info("Player page");
		}

		userSession.setActualPage(roleUrlMap.get(role));
		userSession.setStartTime(Calendar.getInstance().getTime());
		userSession.setUser(user);
	}

	public String logOutAction() {
		try {
			signOut();
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.invalidate();

		} catch (Exception ex) {
			logger.error("Error cerrando la sesión", ex);
		}

		return LOGIN_PAGE;
	}

	public void signOut() {
		User user = userSession.getUser();

		if (user == null)
			return;

		SessionRenderer.removeCurrentSession(user.getLogin());
		user.setOnline(false);
		gameContainerService.logOut(user);
		gameContainerService.unsubscribeListeners(userSession, ludensListeners);
	}

	public String getUsuario() {

		return (this.usuario);
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {

		return (this.password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void changeTab(ActionEvent event) {
		String name = (String) event.getComponent().getAttributes()
				.get("tabName");

		logger.info("Page " + name + ".xhtml");

		userSession.setActualTab(GAME_PATH + name + ".xhtml");
		EnumUserRole role = userSession.getUser().getRole();
		updateViews(role);
	}

	private void updateViews(EnumUserRole role) {
		String[] beansNames = null;

		if (EnumUserRole.ADMIN == role) {
			beansNames = adminViewsControllers;
		}

		if (beansNames == null)
			return;

		for (int i = 0; i < beansNames.length; i++) {

			Object bean = LudensUtilBean.findBean(beansNames[i]);
			UpdateableView updateable = (UpdateableView) bean;

			if (updateable != null) {
				updateable.update();
			}
		}
	}

	public void setUserSession(UserSessionBean userSession) {
		this.userSession = userSession;
	}

	public UserSessionBean getUserSession() {

		return (this.userSession);
	}

	public IncrementableService getIncrementableService() {

		return (this.incrementableService);
	}

	public void setIncrementableService(
			IncrementableService incrementableService) {
		this.incrementableService = incrementableService;
	}

	public void setTalkerChat(ChatTalkerController talkerChat) {
		this.talkerChat = talkerChat;
	}

	public ChatTalkerController getTalkerChat() {

		return (this.talkerChat);
	}

	public GameContainerService getGameContainerService() {

		return (this.gameContainerService);
	}

	public void setGameContainerService(
			GameContainerService gameContainerService) {
		this.gameContainerService = gameContainerService;
	}

	public UserService getUserService() {

		return (this.userService);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<String> getLudensListenersName() {

		return (this.ludensListenersName);
	}

	public void setLudensListenersName(List<String> ludensListenersName) {
		this.ludensListenersName = ludensListenersName;
	}

	public GameService getGameService() {

		return (this.gameService);
	}

	public PlayerService getPlayerService() {

		return (this.playerService);
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
