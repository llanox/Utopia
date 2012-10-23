package co.edu.udea.ludens.services.production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.web.ElementBean;

public class FactorProducer implements ProducerStrategy {
	Logger logger = Logger.getLogger(FactorProducer.class);

	@Override
	public List<ElementBean> produce(Object supplies, ElementProcess process) {
		HashMap<String, Element> elements = (HashMap<String, Element>) supplies;
		List<ElementBean> elementBeans = new ArrayList<ElementBean>();

		for (Object key : elements.keySet()) {

			Element element = elements.get(key);
			Integer level = element.getLevel();

			List<IncrementableConstraint> levelIncrements = element
					.getLevelIncrements();
			// List<IncrementableConstraint> levelIncrements = d.get(level +
			// "");

			Integer production = element.getQuantity();

			if (levelIncrements != null) {
				IncrementableConstraint increment = levelIncrements.get(0);

				if (increment != null) {
					production = increment.getQuantity();
					element.setQuantity(production);
				}
			}

			ElementBean bean = new ElementBean();
			bean.setElement(element);
			bean.setProduction(production);
			bean.setProcess(process);
			elementBeans.add(bean);
		}

		return elementBeans;
	}
}
