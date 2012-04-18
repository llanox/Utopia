package co.edu.udea.ludens.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.enums.EnumElementType;


@Repository
public class ElementDAOImpl extends ObjectDBDAO implements ElementDAO{

	@Override
	public Element findElementByName(String elementName) {
		List<Element> elements = (List<Element>) findObjectByAttribute(Game.class, "name", elementName);
	       if(elements!=null && !elements.isEmpty() )
	    	   return elements.get(0);
			
	       return null;
	}

	@Override
	public List<Element> findElementByType(EnumElementType type,
			String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
