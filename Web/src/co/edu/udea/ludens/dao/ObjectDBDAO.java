package co.edu.udea.ludens.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Updateable;
import co.edu.udea.ludens.exceptions.DatabaseError;

public class ObjectDBDAO implements DBDAO {
	private static Logger logger = Logger.getLogger(ObjectDBDAO.class);

	@PersistenceContext()
	protected EntityManager entityManager;

	@Override()
	public Object save(Object o) {
		logger.info("Guardando " + o);
		entityManager.persist(o);
		entityManager.flush();

		return o;
	}

	@Override()
	public Object saveOrUpdate(Object o) {
		Updateable up = (Updateable) o;

		if (up.getId() == null) {
			logger.info("saving new object " + o);
			entityManager.persist(o);
		} else {
			logger.info("merging old object " + o);
			entityManager.merge(o);
		}
		entityManager.flush();

		return o;
	}

	@Override()
	public void delete(Object o) {
		Object found = entityManager.find(o.getClass(),	((Updateable) o).getId());
		entityManager.remove(found);
	}

	@Override()
	@SuppressWarnings("rawtypes")
	public Object findObjectByAttribute(Class clazz, Object... parameters) {
		StringBuilder sb = new StringBuilder();
		int paramsLength = parameters.length;

		if (paramsLength % 2 != 0) {

			throw new DatabaseError("N�mero incorrecto de parámetros: "
					+ paramsLength);
		}

		for (int i = 0; i < paramsLength; i = i + 2) {
			if (i >= 2) {
				sb.append(" AND ");
			} else {
				sb.append(" WHERE ");
			}

			if (parameters[i + 1] instanceof Boolean) {
				sb.append("o." + parameters[i] + " = :"
						+ removeDot(parameters[i]));
			} else if(parameters[i + 1] instanceof Enum) {
				
				sb.append("o." + parameters[i] + " LIKE :"+ removeDot(parameters[i]));
			}else{

				sb.append("o." + parameters[i] + " LIKE :"+ removeDot(parameters[i]));
				
			}
		}
		logger.info("Clausules: " + sb.toString());
		logger.info("SQL : " + "FROM " + clazz.getSimpleName() + " AS o"+ sb.toString());

		Query query = this.entityManager.createQuery("FROM "
				+ clazz.getSimpleName() + " AS o" + sb.toString());
		for (int i = 0; i < paramsLength; i = i + 2) {
			query.setParameter(removeDot(parameters[i]), parameters[1 + i]);
			logger.info(parameters[i].toString() + " -> " + parameters[1 + i]);
		}

		return (query.getResultList());
	}

	private String removeDot(Object object) {
		String parameter = (String) object;
		StringBuilder sb = new StringBuilder(parameter);
		int index = sb.indexOf(".");

		while (index > -1) {

			sb.deleteCharAt(index);
			index = sb.indexOf(".");
		}

		return sb.toString();
	}

	@Override()
	@SuppressWarnings("rawtypes")
	public Object findObjectByAttributeAndFetch(Class clazz,
			Object... parameters) {
		String JOIN_SQL = "JOIN FETCH";
		StringBuilder sb = new StringBuilder();
		int paramsLength = parameters.length - 1;
		
		if (paramsLength % 2 != 0) {
			throw new DatabaseError("N�mero incorrecto de par�metros: "
					+ paramsLength);
		}

		// for (int i = 0; i < paramsLength; i = i + 2) {
		// if (i >= 2) {
		// sb.append(" AND ");
		// } else {
		// sb.append(" WHERE ");
		// }
		// sb.append("  o." + parameters[i] + ".toString() LIKE '%"
		// + parameters[i + 1] + "' ");
		// }

		for (int i = 0; i < paramsLength; i = i + 2) {
			if (i >= 2) {
				sb.append(" AND ");
			} else {
				sb.append(" WHERE ");
			}

			if (parameters[i + 1] instanceof Boolean) {
				sb.append("o." + parameters[i] + " = :"
						+ removeDot(parameters[i]));
			} else {
				sb.append("o." + parameters[i] + " LIKE :"
						+ removeDot(parameters[i]));
			}
		}
		//sb.append(" ").append(JOIN_SQL).append(" o.")
		//		.append(parameters[paramsLength]);

		logger.info("Clausules: " + sb.toString());
		logger.info("SQL : " + "SELECT o FROM " + clazz.getSimpleName() + " o " + JOIN_SQL + " o."
				+ parameters[paramsLength] + sb.toString());

		//Query query = this.entityManager.createQuery("SELECT o FROM "
		//		+ clazz.getSimpleName() + " AS o" + sb.toString());
		Query query = this.entityManager.createQuery("SELECT o FROM " + clazz.getSimpleName() + " o " + JOIN_SQL + " o."
				+ parameters[paramsLength] + sb.toString());
		for (int i = 0; i < paramsLength; i = i + 2) {
			if (i != paramsLength - 1) {
				query.setParameter(removeDot(parameters[i]), parameters[1 + i]);
				logger.info(parameters[i].toString() + " -> " + parameters[1 + i]);
			}
		}

		// return (query.getResultList());

//		String sqlStatement = "SELECT o FROM " + clazz.getName() + " o "
//				+ JOIN_SQL + " o." + parameters[paramsLength] + " p "
//				+ sb.toString();
//		logger.info("SQL : " + sqlStatement);
//		TypedQuery<Class> q2 = entityManager.createQuery(sqlStatement, clazz);

		return (query.getResultList());
	}

	@Override()
	@SuppressWarnings("rawtypes")
	public Object findObjectByType(Class clazz) {
		// logger.info("Query : " + "SELECT o FROM " + clazz.getSimpleName()
		// + " o");
		// TypedQuery<Class> q2 = em.createQuery(
		// "SELECT c FROM " + clazz.getSimpleName() + " c", clazz);
		logger.info("Query : " + "FROM " + clazz.getSimpleName());
		Query query = this.entityManager.createQuery("FROM "
				+ clazz.getSimpleName());

		return (query.getResultList());
	}

	@Override()
	public void close(Object oc) {
	}

	public EntityManager getEntityManager() {

		return (this.entityManager);
	}
}
