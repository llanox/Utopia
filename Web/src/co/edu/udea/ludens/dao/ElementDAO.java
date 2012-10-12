package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.enums.EnumElementType;

public interface ElementDAO extends FundamentalsOperationsDAO {

	public Element findElementByName(String elementName);

	public List<Element> findElementByType(EnumElementType population,
			String login);
}
