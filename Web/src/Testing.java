import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.enums.EnumUserRole;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.UserService;

public class Testing {

	private static Logger logger = Logger.getLogger(Testing.class);

	public static void main(String[] jgg) throws InterruptedException {

		logger.info("Geting Started");

		String url = "/WebContent/WEB-INF/applicationContext.xml";
		ApplicationContext appContext = new FileSystemXmlApplicationContext(url);

		UserService userService = (UserService) appContext
				.getBean("userService");
		/*GameService gameService = (GameService) appContext
				.getBean("gameService");*/

		User user1 = new User();
		user1.setEmail("correo.del.llanox@gmail.com");
		user1.setGender("Masculino");
		user1.setLogin("llanox");
		user1.setName("Juan Gabriel Gutierrez");
		user1.setOnline(Boolean.FALSE);
		user1.setParticipatingInGame(Boolean.TRUE);
		user1.setPassword("gabo***");
		user1.setRole(EnumUserRole.PLAYER);

		User user2 = new User();
		user2.setEmail("npadierna@gmail.com");
		user2.setGender("Masculino");
		user2.setLogin("rebien");
		user2.setName("Neiber Padierna Perez");
		user2.setOnline(Boolean.TRUE);
		user2.setParticipatingInGame(Boolean.FALSE);
		user2.setPassword("neiber123");
		user2.setRole(EnumUserRole.ADMIN);

		User user3 = new User();
		user3.setEmail("npadierna@gmail.com");
		user3.setGender("Masculino");
		user3.setLogin("rebien");
		user3.setName("Neiber Padierna Perez");
		user3.setOnline(Boolean.TRUE);
		user3.setParticipatingInGame(Boolean.FALSE);
		user3.setPassword("neiber123");
		user3.setRole(EnumUserRole.ADMIN);

		userService.save(user1);
		userService.save(user2);
		userService.save(user3);

		User temp = userService.findUser(user2.getLogin());
		System.out.println("user2: " + user2.getLogin());
		//logger.info("User temp: " + temp.toString());
		//userService.delete(user2);

		if (user1.getId() != null) {
			System.out.println("user1 ID: " + user1.getId().toString());
		} else {
			System.out.println("Doesn't work!!!!");
		}

		List<User> users = userService.findAllUsers();
		for (User user : users) {
			logger.info("User " + user.getLogin());
			logger.info("id " + user.getId());
			logger.info(" " + user.getName());
			logger.info(" " + user.getEmail());
			
			System.out.println("User: " + user.getLogin());
		}



		/*Game game = new Game();
		game.setDuration(1000L);
		game.setLowerThreshold(500L);
		game.setName("Test Gamge");
		game.setProductionTime(1500L);
		game.setStatus(EnumGameStatus.PAUSED);
		game.setUpperThreshold(1500L);
		gameService.save(game);
		gameService.delete(game);*/
	}
}
