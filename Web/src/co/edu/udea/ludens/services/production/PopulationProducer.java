package co.edu.udea.ludens.services.production;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Element;

import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.web.ElementBean;


/*
 * Poblacion (t)= población (t-1) + nacimientos (t) – muertes (t)
 * Muertes (t) = población (t) / expectativa de vida
 * expectativa de vida = 0.65 * cobertura de alimentos * cobertura de agua * cobertura de salud
 * nacimientos(t) = 0.04 * Poblacion (t)
 * 
 * */
public class PopulationProducer implements ProducerStrategy {
	
	Logger logger = Logger.getLogger(PopulationProducer.class);
	
	@Override
	public List<ElementBean> produce(Object supplies,ElementProcess process) {
		
		Element population = (Element) supplies;		
		List<ElementBean> elementBeans = new ArrayList<ElementBean>();
		double populationIncrease  = 1.015; 
		Integer pop = population.getQuantity();	
		Integer nnewpop = (int) (pop*populationIncrease);		
		population.setQuantity(nnewpop);		
		
		ElementBean bean = new ElementBean();
		bean.setElement(population);
		bean.setProduction(0);
		bean.setProcess(process);
		
		elementBeans.add(bean);
		

		
		return elementBeans;
	
	}

}
