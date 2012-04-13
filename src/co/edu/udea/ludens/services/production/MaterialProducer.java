package co.edu.udea.ludens.services.production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.web.ElementBean;

public class MaterialProducer implements ProducerStrategy {
	Logger logger = Logger.getLogger(MaterialProducer.class);


	@Override
	public List<ElementBean> produce(Object supplies, ElementProcess process) {

		HashMap<String, Element> elements = (HashMap<String, Element>) supplies;

	

		List<ElementBean> elementBeans = new ArrayList<ElementBean>();

		for (Object key : elements.keySet()) {

			Element element = elements.get(key);

			Integer production = 0;

			
			Integer level = element.getLevel();
			//LevelConstraint levelIncrement = element.getLevelIncrements().get(level);
			
			
			HashMap<String, List<IncrementableConstraint>> d = element.getLevelIncrements();
					
			List<IncrementableConstraint> levelIncrements =d.get(level+"");
			
			
			
			

			if (levelIncrements != null) {
				
				for (IncrementableConstraint pk : levelIncrements) {

					Integer old = element.getQuantity();
					production = pk.getQuantity();
					Integer nnewQ = old + production;
					element.setQuantity(nnewQ);

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
