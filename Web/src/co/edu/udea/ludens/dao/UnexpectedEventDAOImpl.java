package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.UnexpectedEvent;

public class UnexpectedEventDAOImpl extends ObjectDBDAO implements UnexpectedEventDAO {



	@Override
	public List<UnexpectedEvent> findAllUnexpectedEvents(String gameName) {
		
		List<UnexpectedEvent> events = (List<UnexpectedEvent>) this.findObjectByAttribute(UnexpectedEvent.class, "game.name", gameName);		
		return events;
	}

}
