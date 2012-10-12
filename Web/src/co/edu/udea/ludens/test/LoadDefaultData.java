package co.edu.udea.ludens.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;
import co.edu.udea.ludens.services.UserService;

public class LoadDefaultData {

	@Autowired
	private UserService userService;

	private Logger logger = Logger.getLogger(getClass());

	public void loadData() {
		User admin = userService.findUser("admin");

		if (admin == null) {
			admin = new User();
			admin.setEmail("correo.del.llanox@gmail.com");
			admin.setName("EL q' manda");
			admin.setLogin("admin");
			admin.setPassword("admin");
			admin.setRole(EnumUserRole.ADMIN);
			userService.save(admin);
			logger.info("creando usuario admin");
		} else {
			logger.info("usuario admin ya existe");
		}
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {

		return (this.userService);
	}
}
