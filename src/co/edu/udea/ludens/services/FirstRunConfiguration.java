package co.edu.udea.ludens.services;

import co.edu.udea.ludens.domain.Game;

public interface FirstRunConfiguration {
	
	public void createDefaultUsers();
	public Game createDefaultGame();
	public void loadAllDefaultConfiguration();

}
