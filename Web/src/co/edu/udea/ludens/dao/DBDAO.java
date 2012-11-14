package co.edu.udea.ludens.dao;

public interface DBDAO {

	public abstract Object save(Object o);

	// @Transactional( propagation = Propagation.SUPPORTS,readOnly = false )
	public abstract Object saveOrUpdate(Object o);

	// @Transactional( propagation = Propagation.SUPPORTS,readOnly = false )
	public abstract void delete(Object o);

	// @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
	@SuppressWarnings("rawtypes")
	public abstract Object findObjectByAttribute(Class clazz,
			Object... parameters);

	// @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
	@SuppressWarnings("rawtypes")
	public abstract Object findObjectByType(Class clazz);

	// @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
	@SuppressWarnings("rawtypes")
	public abstract Object findObjectByAttributeAndFetch(Class clazz,
			Object... parameters);

	public abstract void close(Object oc);
}