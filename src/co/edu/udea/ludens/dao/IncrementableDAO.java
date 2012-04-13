package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumElementType;

public interface IncrementableDAO extends FundamentalsOperationsDAO{
	
	public List<Incrementable> findAllByType(EnumElementType type);

	public Incrementable findIncrementableByName(String incrementable);

	public List<Incrementable> findAllIncrementable();

	public List<Incrementable> findAllIncrementable(String game);

	public List<Incrementable> findAllByTypeAndGame(String gameName,EnumElementType material);
	
	
	public List<IncrementableConstraint> findAllLevelConstraint(String gameName,String incrementableName);

	public List<IncrementableConstraint> findAllConstraints();

	public List<IncrementableConstraint> findAllConstraints(String gameName,String incrementableName);

}
