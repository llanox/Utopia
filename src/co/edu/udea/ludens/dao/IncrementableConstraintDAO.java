package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumElementType;

public interface IncrementableConstraintDAO extends FundamentalsOperationsDAO{
	




	public List<IncrementableConstraint> findAllResourceConstraints();

	public List<IncrementableConstraint> findAllResourceConstraints(String incrementable);



}
