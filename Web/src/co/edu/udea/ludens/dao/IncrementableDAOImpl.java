package co.edu.udea.ludens.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumElementType;

@Repository
public class IncrementableDAOImpl extends ObjectDBDAO implements
		IncrementableDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Incrementable> findAllByType(EnumElementType type) {
		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "type", type);

		if (incrementables != null)
			return incrementables;

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Incrementable findIncrementableByName(String incrementableName) {
		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "name", incrementableName);

		if (incrementables != null && !incrementables.isEmpty())
			return incrementables.get(0);

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Incrementable> findAllIncrementable() {
		List<Incrementable> result = (List<Incrementable>) this
				.findObjectByType(Incrementable.class);

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Incrementable> findAllIncrementable(String game) {
		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "game.name", game);

		return incrementables;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Incrementable> findAllByTypeAndGame(String gameName,
			EnumElementType type) {
		List<Incrementable> incrementables = (List<Incrementable>) findObjectByAttribute(
				Incrementable.class, "type", type.name(), "game.name", gameName);

		if (incrementables != null)
			return incrementables;

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IncrementableConstraint> findAllLevelConstraint(
			String gameName, String incrementableName) {
		List<IncrementableConstraint> constraints = (List<IncrementableConstraint>) findObjectByAttribute(
				IncrementableConstraint.class, "incrementable.name",
				incrementableName, "incrementable.game.name", gameName);

		return constraints;
	}

	@Override
	@SuppressWarnings("unchecked")
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
