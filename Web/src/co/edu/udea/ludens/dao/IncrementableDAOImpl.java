package co.edu.udea.ludens.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumElementType;

import org.apache.log4j.Logger;

@Repository
public class IncrementableDAOImpl extends ObjectDBDAO implements
		IncrementableDAO {

	private Logger logger = Logger.getLogger(getClass());

	// @Override
	// public Object saveOrUpdate(Object o) {
	//
	// logger.info("Saving or updating incrementable");
	//
	// Incrementable incr = (Incrementable) o;
	// logger.info("merging game");
	// em.merge(incr.getGame());
	//
	//
	// if(incr.getId()!=null){
	// logger.info("merging incrementable");
	// em.merge(incr);
	// }else{
	//
	// em.persist(incr);
	// logger.info("persisting incrementable");
	// }
	//
	// em.flush();
	//
	// return o;
	// }

	@Override
	public List<Incrementable> findAllByType(EnumElementType type) {

		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "type", type);
		if (incrementables != null)
			return incrementables;

		return null;
	}

	@Override
	public Incrementable findIncrementableByName(String incrementableName) {
		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "name", incrementableName);
		if (incrementables != null && !incrementables.isEmpty())
			return incrementables.get(0);

		return null;
	}

	@Override
	public List<Incrementable> findAllIncrementable() {

		List<Incrementable> result = (List<Incrementable>) this
				.findObjectByType(Incrementable.class);

		return result;
	}

	@Override
	public List<Incrementable> findAllIncrementable(String game) {

		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "game.name", game);

		return incrementables;
	}

	@Override
	public List<Incrementable> findAllByTypeAndGame(String gameName,
			EnumElementType type) {

		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "type", type.name(), "game.name", gameName);

		if (incrementables != null)
			return incrementables;

		return null;
	}

	@Override
	public List<IncrementableConstraint> findAllLevelConstraint(
			String gameName, String incrementableName) {

		List<IncrementableConstraint> constraints = (List<IncrementableConstraint>) findObjectByAttribute(
				IncrementableConstraint.class, "incrementable.name",
				incrementableName, "incrementable.game.name", gameName);
		return constraints;
	}

	@Override
	public List<IncrementableConstraint> findAllConstraints() {

		return (List<IncrementableConstraint>) this
				.findObjectByType(IncrementableConstraint.class);
	}

	@Override
	public List<IncrementableConstraint> findAllConstraints(
			String incrementableName, String gameName) {

		return null;
	}
}
