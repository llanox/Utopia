package co.edu.udea.ludens.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import co.edu.udea.ludens.domain.IncrementableConstraint;


@Repository
public class IncrementableConstraintDAOImpl extends ObjectDBDAO implements
		IncrementableConstraintDAO {

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public List<IncrementableConstraint> findAllResourceConstraints() {
		throw new RuntimeException("you must implement that method .....");
	}

	@Override
	public List<IncrementableConstraint> findAllResourceConstraints(
			String incrementable) {
		throw new RuntimeException("you must implement that method .....");

	}

	@Override
	public Object saveOrUpdate(Object o) {

		IncrementableConstraint cr = (IncrementableConstraint) o;
		logger.info("merging incrementable");
		em.merge(cr.getRestrictedIncrementable());

		if (cr.getId() != null) {
			logger.info("merging IncrementableConstraint");
			em.merge(cr);
		} else {
			em.persist(cr);
			logger.info("persisting IncrementableConstraint");
		}

		em.flush();

		return o;
	}
}
