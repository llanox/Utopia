package co.edu.udea.ludens.services.impl;

import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.edu.udea.ludens.dao.GameDAO;
import co.edu.udea.ludens.dao.PlayerDAO;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.exceptions.InvalidNumberOfFactorsException;
import co.edu.udea.ludens.exceptions.InvalidNumberOfMaterialsException;
import co.edu.udea.ludens.exceptions.InvalidNumberOfPlayersException;
import co.edu.udea.ludens.exceptions.ThereisnotPopulationException;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.util.ConstantsLudens;

@RunWith(MockitoJUnitRunner.class)
public class TestGameService {

	private GameService gameService;

	@Mock
	private PlayerDAO playerDao;

	@Mock
	private GameDAO gameDao;

	@Test(expected = InvalidNumberOfPlayersException.class)
	public void atLeastTwoPlayers() {
		gameService = new GameServiceImpl(gameDao, playerDao);

		Game game = new Game();
		game.setDuration(1000 * 60 * 60 * 24);
		Player player = new Player();

		Incrementable material = new Incrementable();
		material.setType(EnumElementType.MATERIAL);

		Incrementable factor = new Incrementable();
		factor.setType(EnumElementType.FACTOR);

		Incrementable population = new Incrementable();
		population.setType(EnumElementType.POPULATION);

		game.getDefaultIncrementables().add(material);
		game.getDefaultIncrementables().add(factor);
		game.getDefaultIncrementables().add(population);

		player.setGame(game);

		game.getPlayers().add(player);

		when(playerDao.findAllPlayersByGameName(true, game.getName()))
				.thenReturn(game.getPlayers());

		gameService.meetRequirements(game);
	}

	@Test(expected = InvalidNumberOfMaterialsException.class)
	public void atLeastOneMaterial() {
		gameService = new GameServiceImpl(gameDao, playerDao);
		Game game = new Game();
		game.setDuration(1000 * 60 * 60 * 24);
		Player player = new Player();
		Player player1 = new Player();

		// Incrementable material = new Incrementable();
		// material.setType(EnumElementType.MATERIAL);

		Incrementable factor = new Incrementable();
		factor.setType(EnumElementType.FACTOR);

		Incrementable population = new Incrementable();
		population.setType(EnumElementType.POPULATION);

		// game.getDefaultIncrementables().add(material);
		game.getDefaultIncrementables().add(factor);
		game.getDefaultIncrementables().add(population);

		player.setGame(game);

		game.getPlayers().add(player);
		game.getPlayers().add(player1);

		when(playerDao.findAllPlayersByGameName(true, game.getName()))
				.thenReturn(game.getPlayers());

		gameService.meetRequirements(game);
	}

	@Test(expected = InvalidNumberOfFactorsException.class)
	public void atLeastOneFactor() {
		gameService = new GameServiceImpl(gameDao, playerDao);
		Game game = new Game();
		game.setDuration(1000 * 60 * 60 * 24);
		Player player = new Player();
		Player player1 = new Player();

		Incrementable material = new Incrementable();
		material.setType(EnumElementType.MATERIAL);

		// Incrementable factor = new Incrementable();
		// factor.setType(EnumElementType.FACTOR);

		Incrementable population = new Incrementable();
		population.setType(EnumElementType.POPULATION);

		game.getDefaultIncrementables().add(material);
		// game.getDefaultIncrementables().add(factor);
		game.getDefaultIncrementables().add(population);

		player.setGame(game);

		game.getPlayers().add(player);
		game.getPlayers().add(player1);

		when(playerDao.findAllPlayersByGameName(true, game.getName()))
				.thenReturn(game.getPlayers());

		gameService.meetRequirements(game);
	}

	@Test(expected = ThereisnotPopulationException.class)
	public void thereisnotPopulation() {
		gameService = new GameServiceImpl(gameDao, playerDao);
		Game game = new Game();
		game.setDuration(1000 * 60 * 60 * 24);
		Player player = new Player();
		Player player1 = new Player();

		Incrementable material = new Incrementable();
		material.setType(EnumElementType.MATERIAL);

		Incrementable factor = new Incrementable();
		factor.setType(EnumElementType.FACTOR);

		game.getDefaultIncrementables().add(material);
		game.getDefaultIncrementables().add(factor);

		player.setGame(game);

		game.getPlayers().add(player);
		game.getPlayers().add(player1);

		when(playerDao.findAllPlayersByGameName(true, game.getName()))
				.thenReturn(game.getPlayers());

		gameService.meetRequirements(game);
	}

	@Test
	public void generateTwoEventsByFactorOrMaterial() {
		Game game = new Game();
		gameService = new GameServiceImpl(gameDao, playerDao);

		Incrementable factor = new Incrementable();
		factor.setName("Salud");
		factor.setType(EnumElementType.FACTOR);

		game.getDefaultIncrementables().add(factor);

		Incrementable material = new Incrementable();
		material.setName("Madera");
		material.setType(EnumElementType.MATERIAL);

		game.getDefaultIncrementables().add(material);

		gameService.generateUnexpectedEvents(game);

		Assert.assertEquals(
				"No se han generado 2 eventos por factor o material", 4, game
						.getUnexpectedEvents().size());
	}

	@Test
	public void generateUnexpectedEventBetweenRange() {
		Game game = new Game();
		gameService = new GameServiceImpl(gameDao, playerDao);

		Incrementable factor = new Incrementable();
		factor.setName("Salud");
		factor.setType(EnumElementType.FACTOR);

		game.getDefaultIncrementables().add(factor);

		gameService.generateUnexpectedEvents(game);
		Assert.assertNotNull("No puede ser nulo el evento retornado", game
				.getUnexpectedEvents().get(0));
		int quantity = game.getUnexpectedEvents().get(0).getQuantity();
		Assert.assertTrue(
				"Los porcentajes de afectación no estan en el rango permitido: "
						+ quantity, quantity >= ConstantsLudens.LOWER_THRESHOLD
						&& quantity <= ConstantsLudens.UPPER_THRESHOLD);
	}
}
