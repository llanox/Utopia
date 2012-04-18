package co.edu.udea.ludens.services.production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.util.UtopiaUtil;
import co.edu.udea.ludens.web.ElementBean;

public class FactorProducerWithGrowthRate implements ProducerStrategy {
	
	Logger logger = Logger.getLogger(FactorProducerWithGrowthRate.class);
	
	@Override
	public List<ElementBean> produce(Object supplies,ElementProcess process) {
		HashMap<String, Element> elements = (HashMap<String, Element>) supplies;
		List<ElementBean> elementBeans = new ArrayList<ElementBean>();



		for (Object key : elements.keySet()) {

			Element element = elements.get(key);
			Player player = process.getPlayer();
			

		    UtopiaUtil.calculateCoverage(element, player);
			
	 

			ElementBean bean = new ElementBean();
			bean.setElement(element);
			bean.setProduction(0);
			bean.setProcess(process);
			elementBeans.add(bean);

		}

		return elementBeans;
	}



}
