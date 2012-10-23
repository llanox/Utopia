import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.UserService;

public class Testing {

	private static Logger logger = Logger.getLogger(Testing.class);

	public static void main(String[] jgg) throws InterruptedException {

		logger.info("Geting Started");
		String url = "//media/Data/DropBox/Dropbox/Projects/Web/Utopia/WebContent/WEB-INF/applicationContext.xml";
		// String url =
		// "C:\\Documents and Settings\\Adriel\\Escritorio\\Gabriel\\Dropbox\\Utopo\\WebContent\\WEB-INF\\applicationContext.xml";
		ApplicationContext appContext = new FileSystemXmlApplicationContext(url);
		String filePath = "/media/Data/DropBox/Dropbox/Projects/Web/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/db4oTest.db4";
		UserService userService = (UserService) appContext
				.getBean("userService");
		PlayerService playerService = (PlayerService) appContext
				.getBean("playerService");

		// User user1 = new User();
		// user1.setLogin("3333333");
		// user1.setName("El 13 33's");
		// user1.setGender("Masculino");
		// user1.setPassword("222222");
		// user1.setEmail("correo.del.llanox@gmail.com");
		//
		// user1 = userService.save(user1);

		User user1 = userService.findUser("45333");
		user1.setLogin("111111111");
		user1.setGender("Male");

		userService.save(user1);

		// User user = userService.findUser("pablin");
		// logger.info("login "+user.getLogin());
		List<User> users = userService.findAllUsers();

		for (User user : users) {
			logger.info("User " + user.getLogin());
			logger.info("id " + user.getId());
			logger.info(" " + user.getName());
			logger.info(" " + user.getEmail());
		}
		//
		// Player player1 = new Player();
		// player1.setStartTime(0l);
		// player1.setUser(user1);
		//
		// player1 = playerService.save(player1);
		//
		// List<Player> players = playerService.findAllPlayers();
		//
		// for(Player player:players){
		//
		//
		// logger.info("login "+player.getUser().getLogin());
		// }
		//
	}
}
