import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.services.GameService;

public class TestingGames {

	private static Logger logger = Logger.getLogger(TestingGames.class);

	public static void main(String[] args) {
		logger.info("Geting Started");

		String url = "/WebContent/WEB-INF/applicationContext.xml";
		ApplicationContext appContext = new FileSystemXmlApplicationContext(url);

		GameService gameService = (GameService) appContext
				.getBean("gameService");

		Game game1 = new Game();
		game1.setDuration(1000L);
		game1.setEndTime(new Date());
		game1.setLowerThreshold(500L);
		game1.setName("Game #1");
		game1.setProductionTime(1500L);
		game1.setStartTime(new Date());
		game1.setStatus(EnumGameStatus.PAUSED);
		game1.setUpperThreshold(2000L);

//		Game game2 = new Game();
//		game2.setDuration(1000L);
//		game2.setEndTime(new Date());
//		game2.setLowerThreshold(500L);
//		game2.setName("Game #2");
//		game2.setProductionTime(1500L);
//		game2.setStartTime(new Date());
//		game2.setStatus(EnumGameStatus.PAUSED);
//		game2.setUpperThreshold(2000L);

		gameService.save(game1);
//		gameService.save(game2);

		List<Game> games = gameService.findAllGames();
		for (Game game : games) {
			System.out.println("Name: " + game.getName() + " ID: "
					+ game.getId() + " Status: " + game.getStatus());
		}

//		gameService.delete(game2);

		Game game = gameService.findGameByName("Game #1");
		System.out.println("Name: " + game.getName() + " ID: " + game.getId()
				+ " Status: " + game.getStatus());
	}
}
