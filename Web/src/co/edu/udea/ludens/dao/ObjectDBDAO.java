package co.edu.udea.ludens.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Updateable;
import co.edu.udea.ludens.exceptions.DatabaseError;

public class ObjectDBDAO implements DBDAO {
	private static Logger logger = Logger.getLogger(ObjectDBDAO.class);

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Object save(Object o) {
		logger.info("Guardando " + o);
		em.persist(o);
		em.flush();

		return o;
	}

	@Override
	public Object saveOrUpdate(Object o) {
		Updateable up = (Updateable) o;

		if (up.getId() == null) {
			logger.info("saving new object " + o);
			em.persist(o);
		} else {
			logger.info("merging old object " + o);
			em.merge(o);
		}
		em.flush();

		return o;
	}

	@Override
	public void delete(Object o) {
		Object found = em.find(o.getClass(), ((Updateable) o).getId());

		em.remove(found);
	}

	@Override
	public Object findObjectByAttribute(Class clazz, Object... parameters) {
		StringBuilder sb = new StringBuilder();
		int paramsLength = parameters.length;

		// Garantiza que funcione con 2 > parametros y para cualquier tipo de
		// dato.
		if (paramsLength % 2 != 0) {

			throw new DatabaseError("Número incorrecto de parámetros: "
					+ paramsLength);
		}

		for (int i = 0; i < paramsLength; i = i + 2) {
			if (i >= 2) {
				sb.append(" AND ");
			} else {
				sb.append(" WHERE ");
			}
			sb.append("o." + parameters[i] + " LIKE :"
					+ parameters[i + 1]);
		}
		logger.info("Clausules: " + sb.toString());

		// CriteriaQuery<Object> query1 =
		// CriteriaQuery<Object> q = em.getCriteriaBuilder().createQuery();
		logger.info("SQL : " + "FROM " + clazz.getSimpleName() + " AS o"
				+ sb.toString());
		// TypedQuery<Class> q2 = em.createQuery(
		// "SELECT o FROM " + clazz.getName() + " o " + sb.toString(),
		// clazz);

		Query query = this.em.createQuery("FROM " + clazz.getSimpleName()
				+ " AS o" + sb.toString());
		for (int i = 0; i < paramsLength; i = i + 2) {
			query.setParameter(parameters[i].toString(), parameters[1 + i]);
			logger.info(parameters[i].toString() + " -> " + parameters[1 + i]);
		}

		return (query.getResultList());
		// return (q2.getResultList());
	}

	@Override
	public Object findObjectByAttributeAndFetch(Class clazz,
			Object... parameters) {
		String JOIN_SQL = "INNER JOIN";
		StringBuilder sb = new StringBuilder();
		// Elimina el atributo al cual se le quiere hacer el fetch
		int paramsLength = parameters.length - 1;

		// Garantiza que funcione con 2 > parametros y para cualquier tipo de
		// dato.
		if (paramsLength % 2 != 0) {
			throw new DatabaseError("NÃºmero incorrecto de parÃ¡metros: "
					+ paramsLength);
		}

		for (int i = 0; i < paramsLength; i = i + 2) {
			if (i >= 2) {
				sb.append(" AND ");
			} else {
				sb.append(" WHERE ");
			}
			sb.append("  o." + parameters[i] + ".toString() LIKE '%"
					+ parameters[i + 1] + "' ");
		}

		CriteriaQuery<Object> query = em.getCriteriaBuilder().createQuery();
		String sqlStatement = "SELECT o FROM " + clazz.getName() + " o "
				+ JOIN_SQL + " o." + parameters[paramsLength] + " p "
				+ sb.toString();
		logger.info("SQL : " + sqlStatement);
		TypedQuery<Class> q2 = em.createQuery(sqlStatement, clazz);

		return (q2.getResultList());
	}

	@Override
	public Object findObjectByType(Class clazz) {
		// logger.info("Query : " + "SELECT o FROM " + clazz.getSimpleName()
		// + " o");
		// TypedQuery<Class> q2 = em.createQuery(
		// "SELECT c FROM " + clazz.getSimpleName() + " c", clazz);
		logger.info("Query : " + "FROM " + clazz.getSimpleName());
		Query query = this.em.createQuery("FROM " + clazz.getSimpleName());

		if (query.getResultList().isEmpty()) {
			logger.info("The query.getResultList() is empty...");
		}

		return (query.getResultList());
		// return q2.getResultList();
	}

	@Override
	public void close(Object oc) {
	}

	public EntityManager getEm() {

		return (this.em);
	}
}
