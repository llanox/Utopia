package co.edu.udea.ludens.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.ludens.dao.GameDAO;
import co.edu.udea.ludens.dao.IncrementableConstraintDAO;
import co.edu.udea.ludens.dao.IncrementableDAO;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.IncrementableService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class IncrementableServiceImpl implements IncrementableService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private IncrementableDAO incrementableDao;

	@Autowired
	private GameDAO gameDao;

	@Autowired
	private IncrementableConstraintDAO incrementableConstraintDao;

	/**
	 * @param incrementableDao
	 *            the incrementableDao to set
	 */
	void setIncrementableDao(IncrementableDAO incrementableDao) {
		this.incrementableDao = incrementableDao;
	}

	/**
	 * @return the incrementableDao
	 */
	IncrementableDAO getIncrementableDao() {

		return (this.incrementableDao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void save(Incrementable incrementable) {
		incrementableDao.saveOrUpdate(incrementable);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(Incrementable incrementable) {
		incrementableDao.delete(incrementable);
	}

	@Override
	public Incrementable findIncrementableByName(String name) {
		Incrementable incr = incrementableDao.findIncrementableByName(name);

		return incr;
	}

	@Override
	public List<Incrementable> getAllIncrementables() {

		return (incrementableDao.findAllIncrementable());
	}

	@Override
	public List<Incrementable> getAllIncrementablesGame(String game) {

		return (incrementableDao.findAllIncrementable(game));
	}

	/**
	 * @param gameDao
	 *            the gameDao to set
	 */
	public void setGameDao(GameDAO gameDao) {
		this.gameDao = gameDao;
	}

	/**
	 * @return the gameDao
	 */
	public GameDAO getGameDao() {

		return (this.gameDao);
	}

	@Override
	public List<Incrementable> getAllIncrementablesGame(String gameName,
			EnumElementType material) {
		List<Incrementable> materials = incrementableDao.findAllByTypeAndGame(
				gameName, material);

		return materials;
	}

	@Override
	public void save(IncrementableConstraint constraint) {
		incrementableDao.saveOrUpdate(constraint);
	}

	@Override
	public List<IncrementableConstraint> getAllConstraints() {

		return (incrementableDao.findAllConstraints());
	}

	@Override
	public List<IncrementableConstraint> getAllConstraints(String gameName,
			String incrementableName) {

		return (incrementableDao.findAllLevelConstraint(gameName,
				incrementableName));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void createResourceConstraints(Incrementable actualIncrementable)
			throws LudensException {
		if (actualIncrementable.getGame() == null)
			throw new LudensException(
					"No fue definido un juego para el actual incrementable");

		List<Incrementable> materials = getAllIncrementablesGame(
				actualIncrementable.getGame().getName(),
				EnumElementType.MATERIAL);

		logger.info("total materials " + materials.size());
		logger.info(" incrementable " + actualIncrementable.getName());
		logger.info(" game ... " + actualIncrementable.getGame().getName());
		logger.info(" Constraints size "
				+ actualIncrementable.getConstraints().size());

		for (Incrementable incr : materials) {
			if (contains(actualIncrementable.getConstraints(), incr)) {
				continue;
			}

			logger.info("Creando constraint " + incr.getName() + " for "
					+ actualIncrementable.getName());

			IncrementableConstraint constraint = new IncrementableConstraint();
			constraint.setElementName(incr.getName());
			constraint.setRestrictedIncrementable(actualIncrementable);
			incrementableConstraintDao.saveOrUpdate(constraint);
		}
	}

	private boolean contains(List<IncrementableConstraint> constraints,
			Incrementable incr) {
		for (IncrementableConstraint lc : constraints) {
			if (incr.getName().equalsIgnoreCase(lc.getElementName())) {
				logger.info("Sí esta " + incr.getName());

				return true;
			}
		}

		return false;
	}

	public void setIncrementableConstraintDao(
			IncrementableConstraintDAO incrementableConstraintDao) {
		this.incrementableConstraintDao = incrementableConstraintDao;
	}

	public IncrementableConstraintDAO getIncrementableConstraintDao() {

		return (this.incrementableConstraintDao);
	}

	@Override
	public void deleteResourceConstraints(Incrementable actualIncrementable)
			throws LudensException {
		if (actualIncrementable.getGame() == null)
			throw new LudensException(
					"No fue definido un juego para el actual incrementable");

		for (IncrementableConstraint constraint : actualIncrementable
				.getConstraints()) {

			logger.info("Deleting constraint for "
					+ constraint.getElementName());
			incrementableConstraintDao.delete(constraint);
		}
	}
}
