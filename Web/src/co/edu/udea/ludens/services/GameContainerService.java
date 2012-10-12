package co.edu.udea.ludens.services;

import java.util.List;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.util.LudensListener;
import co.edu.udea.ludens.web.UserSessionBean;

public interface GameContainerService extends SystemContainer {

	public void suscribeListeners(Game game, User user,
			List<LudensListener> listeners);

	public void unsuscribeListeners(Game game, User user,
			List<LudensListener> listeners);

	public void startGame(Game game) throws LudensException;

	public void suscribeListeners(UserSessionBean userSession,
			List<LudensListener> ludensListeners);

	public void unsubscribeListeners(UserSessionBean userSession,
			List<LudensListener> ludensListeners);
}
