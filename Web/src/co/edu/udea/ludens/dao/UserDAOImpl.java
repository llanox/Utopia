package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends ObjectDBDAO implements UserDAO {

	public UserDAOImpl() {
	}

	@SuppressWarnings("unchecked")
	public User findUserByLogin(String login) {
		User user = null;
		List<User> result = (List<User>) (findObjectByAttribute(User.class,
				"login", login));

		if (result != null && !result.isEmpty()) {
			user = result.get(0);
		}

		return (user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		List<User> result = (List<User>) (this.findObjectByType(User.class));

		return (result);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findUsersBy(boolean participatingInGame) {
		List<User> result = (List<User>) findObjectByAttribute(User.class,
				"participatingInGame", participatingInGame);

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findUsersBy(boolean participatingInGame, EnumUserRole role) {
		List<User> result = (List<User>) findObjectByAttribute(User.class,
				"participatingInGame", participatingInGame, "role", role);

		return result;
	}
}