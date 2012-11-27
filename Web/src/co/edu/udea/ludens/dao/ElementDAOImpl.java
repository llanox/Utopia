package co.edu.udea.ludens.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.enums.EnumElementType;

@Repository()
public class ElementDAOImpl extends ObjectDBDAO implements ElementDAO {

	@Override()
	@SuppressWarnings("unchecked")
	public Element findElementByName(String elementName) {
		List<Element> elements = (List<Element>) findObjectByAttribute(
				Game.class, "name", elementName);

		if (elements != null && !elements.isEmpty())
			return elements.get(0);

		return null;
	}

	@Override()
	@SuppressWarnings("unchecked")
	public List<Element> findElementByType(EnumElementType type, String login) {
		List<Element> elements = (List<Element>) findObjectByAttribute(
				Element.class, "incrementable.type", type, "player.user.login",
				login);

		return elements;
	}
}
