package co.edu.udea.ludens.services;

import java.util.List;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;

public interface UserService {

	public User save(User user);

	public List<User> findAllUsers();

	public User validateUser(String usuario, String password);

	public User findUser(String login);

	public void delete(User actualUser);

	public List<User> findUserByRole(boolean playing, EnumUserRole player);
}
