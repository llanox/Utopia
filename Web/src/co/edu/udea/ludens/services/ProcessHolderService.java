package co.edu.udea.ludens.services;

public interface ProcessHolderService {

	/**
	 * Find by primary key.
	 * 
	 * @param entityClass
	 * @param primaryKey
	 * @return the found entity instance or null if the entity does not exist
	 * @throws IllegalArgumentException
	 *             if the first argument does not denote an entity type or the
	 *             second argument is not a valid type for that entity's primary
	 *             key
	 */
	@SuppressWarnings("rawtypes")
	public Process findProcessById(Class entityClass, Object primaryKey);

	public void putProcess(Object primaryKey, Process process);

	public Process removeProcess(Object primaryKey);
}