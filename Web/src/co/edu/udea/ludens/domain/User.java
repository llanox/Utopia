package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.enums.EnumUserRole;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity()
@Table(name = "users")
public class User implements Serializable, Updateable {

	@Transient()
	private static final long serialVersionUID = 1739544489L;

	@Id()
	@GeneratedValue()
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "login")
	private String login;
	@Column(name = "email")
	private String email;
	@Column(name = "gender")
	private String gender;
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;
	@Column(name = "role")
	private EnumUserRole role;
	@Column(name = "password")
	private String password;
	@Column(name = "on_line")
	private Boolean online = false;
	@Column(name = "participating_in_game")
	private Boolean participatingInGame = false;

	public User() {
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Long getId() {

		return (this.id);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return (this.name);
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {

		return (this.login);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {

		return (this.email);
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {

		return (this.gender);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {

		return (this.password);
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Boolean isOnline() {

		return (this.online);
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDate() {

		return (this.birthDate);
	}

	public void setRole(EnumUserRole role) {
		this.role = role;
	}

	public EnumUserRole getRole() {

		return (this.role);
	}

	public void setParticipatingInGame(Boolean participatingInGame) {
		this.participatingInGame = participatingInGame;
	}

	public Boolean isParticipatingInGame() {

		return (this.participatingInGame);
	}

	@Override
	public void updateWith(Object o) {
		User newUser = (User) o;
		this.name = newUser.name;
		this.login = newUser.login;
		this.email = newUser.email;
		this.gender = newUser.gender;
		this.birthDate = newUser.birthDate;
		this.role = newUser.role;
		this.password = newUser.password;
		this.online = newUser.online;
		this.participatingInGame = newUser.participatingInGame;
	}
}