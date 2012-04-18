package co.edu.udea.ludens.services;

import java.util.List;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.exceptions.LudensException;

public interface IncrementableService {
	
	public void save(Incrementable incrementable);
	public void delete(Incrementable incrementable);
	public Incrementable findIncrementableByName(String name);
	public List<Incrementable> getAllIncrementables();
	public List<Incrementable> getAllIncrementablesGame(String game) ;
	public List<Incrementable> getAllIncrementablesGame(String name,EnumElementType material);
	public void save(IncrementableConstraint constraint);
	public List<IncrementableConstraint> getAllConstraints();
	public List<IncrementableConstraint> getAllConstraints(String name, String name2);
	public void createResourceConstraints(Incrementable actualIncrementable) throws LudensException;
	

}
