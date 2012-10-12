package co.edu.udea.ludens.services.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.edu.udea.ludens.dao.ElementDAO;
import co.edu.udea.ludens.dao.GameDAO;
import co.edu.udea.ludens.dao.PlayerDAO;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.services.ElementService;

@RunWith(MockitoJUnitRunner.class)
public class TestElementService {

	private ElementService elementService;

	@Mock
	private GameDAO gameDao;

	@Mock
	private PlayerDAO playerDao;

	@Mock
	private ElementDAO elementDao;

	@Test
	public void createAllElementForEachPlayer() {
		Game game = new Game();
		elementService = new ElementServiceImpl(gameDao, playerDao, elementDao);

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

		int incrementables = game.getDefaultIncrementables().size();
		int counter = 0;
		elementService.createElementsForEachPlayer(game);

		List<Player> players = game.getPlayers();
		boolean testOk = true;
		for (Player p : players) {
			counter = 0;
			if (p.getDevelopmentFactors() != null)
				counter = p.getDevelopmentFactors().size();

			if (p.getMaterials() != null)
				counter = counter + p.getMaterials().size();

			if (p.getPopulation() != null) {
				counter = counter + 1;
			}
			if (counter != incrementables) {
				testOk = false;
				break;
			}
		}

		Assert.assertEquals(
				"No se crearon todos los elementos a partir de los incrementables del juego "
						+ counter + " de " + incrementables, true, testOk);
	}
}
