package co.edu.udea.ludens.services.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.SystemContainer;
import co.edu.udea.ludens.services.UserService;

public class TestAppCtx {

	private SystemContainer systemContainer;
	private GameService gameService;
	private PlayerService playerService;
	private String gameName;
	private UserService userService;
	private String userName1;
	private String userName2;
	private String GAME_NAME = "MaestroDF8";

	@Before
	public void preparing() {
		String url = "//media/Data/DropBox/Dropbox/Projects/Web/Utopia2/Utopia/WebContent/WEB-INF/applicationContext.xml";
		ApplicationContext appContext = new FileSystemXmlApplicationContext(url);
		systemContainer = appContext.getBean("systemContainer",
				SystemContainer.class);
		gameService = appContext.getBean("gameService", GameService.class);
		userService = appContext.getBean("userService", UserService.class);
		playerService = appContext
				.getBean("playerService", PlayerService.class);

		// Game game = new Game();
		// game.setName(GAME_NAME);
		// gameName = game.getName();
		//
		//
		// Incrementable material = new Incrementable();
		// material.setType(EnumElementType.MATERIAL);
		//
		// Incrementable factor = new Incrementable();
		// factor.setType(EnumElementType.FACTOR);
		//
		// Incrementable population = new Incrementable();
		// population.setType(EnumElementType.POPULATION);
		//
		// game.getDefaultIncrementables().add(material);
		// game.getDefaultIncrementables().add(factor);
		// game.getDefaultIncrementables().add(population);
		//
		//
		//
		//
		// userName1="User1"+System.currentTimeMillis();
		// User user1 = new User();
		// user1.setLogin(userName1);
		// userName2="User2"+System.currentTimeMillis();
		//
		// User user2 = new User();
		// user2.setLogin(userName2);
		// userService.save(user2);
		//
		// Player player1 = new Player();
		// player1.setUser(user1);
		//
		// gameService.addPlayer(game, player1);
		//
		//
		// Player player2 = new Player();
		// player2.setUser(user2);
		//
		// gameService.save(game);
		// gameService.addPlayer(game, player2);
		//
		//
	}

	@Test
	public void modifyingGame() {
		Game game = gameService.findGameByName(GAME_NAME);
		List<Player> players = playerService.findAllPlayersByGameName(true,
				game.getName());

		game.setPlayers(players);

		// systemContainer.startGame(game);
		String name = game.getName();
		//
		// boolean startsWith = name.startsWith("Maestro");
		//
		// Assert.assertEquals("NO modifico el juego", true, startsWith);
		Assert.assertNotNull("El juego no deberia ser null", game);
		Assert.assertEquals("NO modifico el juego", EnumGameStatus.STARTED,
				game.getStatus());
	}
}
