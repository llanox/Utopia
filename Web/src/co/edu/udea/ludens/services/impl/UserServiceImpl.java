package co.edu.udea.ludens.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.ludens.dao.UserDAO;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;
import co.edu.udea.ludens.services.UserService;

@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserDAO userDao;

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * @return the userDao
	 */
	public UserDAO getUserDao() {

		return (this.userDao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public User save(User user) {
		user = (User) userDao.saveOrUpdate(user);

		return user;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<User>();

		users = userDao.findAllUsers();

		return users;
	}

	@Override
	public User validateUser(String login, String password) {
		User user = userDao.findUserByLogin(login);
		logger.info("Found User -->" + user);
		if (user == null)
			return null;

		String userPassw = user.getPassword();

		if (userPassw == null || !userPassw.equalsIgnoreCase(password))
			return null;

		return user;
	}

	@Override
	public User findUser(String login) {
		User user = userDao.findUserByLogin(login);

		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public List<User> findUserByRole(boolean playing, EnumUserRole role) {
		List<User> users = userDao.findUsersBy(playing, role);

		return users;
	}
}
