package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.icesoft.faces.component.ext.RowSelectorEvent;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumUserRole;
import co.edu.udea.ludens.services.UserService;

public class UserController {

	private User actualUser = new User();
	private String repeatedPassword;
	private UserService userService;
	private List<SelectItem> roles = new ArrayList<SelectItem>();
	private List<User> users = new ArrayList<User>();
	private boolean editingUser = false;
	private UIData tableUsers;

	private Logger logger = Logger.getLogger(getClass());

	@PostConstruct
	public void loadData() {
		logger.info("Loading data");
		users = userService.findAllUsers();
		roles = getRoles();
	}

	public void newUser(javax.faces.event.ActionEvent event) {
		editingUser = true;
	}

	public void saveUser(javax.faces.event.ActionEvent event) {
		editingUser = false;
		userService.save(actualUser);
		actualUser = new User();
		users = userService.findAllUsers();
	}

	public void deleteUser(javax.faces.event.ActionEvent event) {
		editingUser = false;
		int selectedRowIndex = tableUsers.getRowIndex();
		setActualUser(users.get(selectedRowIndex));

		userService.delete(actualUser);
		actualUser = new User();
		users = userService.findAllUsers();
	}

	public void editUser(javax.faces.event.ActionEvent event) {
		editingUser = true;
		int selectedRowIndex = tableUsers.getRowIndex();
		setActualUser(users.get(selectedRowIndex));
		repeatedPassword = actualUser.getPassword();
	}

	public void cancelEditing(javax.faces.event.ActionEvent event) {
		editingUser = false;
		setActualUser(new User());
	}

	public List<SelectItem> getRoles() {
		if (roles == null || roles.isEmpty()) {
			roles = new ArrayList<SelectItem>();

			logger.info("num " + EnumUserRole.values().length);

			for (int i = 0; i < EnumUserRole.values().length; i++) {
				SelectItem item = new SelectItem();
				item.setLabel(EnumUserRole.values()[i].getRoleName());
				item.setValue(EnumUserRole.values()[i]);
				roles.add(item);
				logger.info("Roles " + EnumUserRole.values()[i]);
			}
		}

		return roles;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {

		return (this.users);
	}

	/**
	 * @param repeatedPassword
	 *            the repeatedPassword to set
	 */
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	/**
	 * @return the repeatedPassword
	 */
	public String getRepeatedPassword() {

		return (this.repeatedPassword);
	}

	/**
	 * @param editingUser
	 *            the editingUser to set
	 */
	public void setEditingUser(boolean editingUser) {
		this.editingUser = editingUser;
	}

	/**
	 * @return the editingUser
	 */
	public boolean isEditingUser() {

		return (this.editingUser);
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

	/**
	 * @param tableUsers
	 *            the tableUsers to set
	 */
	public void setTableUsers(UIData tableUsers) {
		this.tableUsers = tableUsers;
	}

	/**
	 * @return the tableUsers
	 */
	public UIData getTableUsers() {

		return (this.tableUsers);
	}

	/**
	 * @param actualUser
	 *            the actualUser to set
	 */
	public void setActualUser(User actualUser) {
		this.actualUser = actualUser;
	}

	/**
	 * @return the actualUser
	 */
	public User getActualUser() {

		return (this.actualUser);
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<SelectItem> roles) {
		this.roles = roles;
	}
}
