package co.edu.udea.ludens.services;

import java.util.Set;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.enums.EnumElementType;

public interface ElementService {

	public Set<Element> getAllElements(EnumElementType elementType);

	public Set<Element> getAllElements(String idUser);

	public Set<Element> getFactors(String playerName);

	public Set<Element> getMaterials(String playerName);

	public void save(Element element);

	public void delete(Element element);

	public MessageEvent upLevel(String playerName, String elementName,
			GameProcess gameProcess);

	public Element findElementByName(String elementName);

	public Element getPopulation(String login);

	public void createElementsForEachPlayer(Game game);

	public Set<Element> getAllElementsByPlayer(EnumElementType material,
			String login);
}
