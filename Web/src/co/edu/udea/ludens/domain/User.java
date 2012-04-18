package co.edu.udea.ludens.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.udea.ludens.enums.EnumUserRole;

@Entity
public class User  implements Updateable {
	


	@Id @GeneratedValue
    private Long id;
	private String name;	
	private String login;	
	private String email;	
	private String gender;
	@Temporal(TemporalType.DATE)
	private Date birthDate;	
	private EnumUserRole role;	
	private String password;
	private Boolean online=false;
    private Boolean participatingInGame=false;


	

	public User() {
	
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @generated
	 */
	public void setName(String name) {
	
		this.name = name;
	}

	/**
	 * @generated
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * @generated
	 */
	public void setLogin(String login) {
	
		this.login = login;
	}

	/**
	 * @generated
	 */
	public String getLogin() {

		return this.login;
	}

	/**
	 * @generated
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	/**
	 * @generated
	 */
	public String getEmail() {

		return this.email;
	}

	/**
	 * @generated
	 */
	public void setGender(String gender) {

		this.gender = gender;
	}

	/**
	 * @generated
	 */
	public String getGender() {
	
		return this.gender;
	}




	/**
	 * @generated
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * @generated
	 */
	public String getPassword() {

		return this.password;
	}



	/**
	 * @param online the online to set
	 */
	public void setOnline(Boolean online){

		this.online = online;
	}

	/**
	 * @return the online
	 */
	public Boolean isOnline() {
	
		return online;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {

		this.birthDate = birthDate;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {

		return birthDate;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(EnumUserRole role) {

		this.role = role;
	}

	/**
	 * @return the role
	 */
	public EnumUserRole getRole() {

		return role;
	}

	


	
	


	/**
	 * @param participatingInGame the participatingInGame to set
	 */
	public void setParticipatingInGame(Boolean participatingInGame) {
		
		this.participatingInGame = participatingInGame;
	}

	/**
	 * @return the participatingInGame
	 */
	public Boolean isParticipatingInGame() {
	
		return participatingInGame;
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
