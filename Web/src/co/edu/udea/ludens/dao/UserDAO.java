package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;

public interface UserDAO extends FundamentalsOperationsDAO {

	public User findUserByLogin(String login);

	public List<User> findAllUsers();

	public List<User> findUsersBy(boolean playing);

	public List<User> findUsersBy(boolean playing, EnumUserRole role);
}
