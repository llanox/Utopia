package co.edu.udea.ludens.services.production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.util.UtopiaUtil;
import co.edu.udea.ludens.web.ElementBean;

public class MaterialProducerWithGrowthRate implements ProducerStrategy {
	
	Logger logger = Logger.getLogger(MaterialProducerWithGrowthRate.class);
	
	@Override
	public List<ElementBean> produce(Object supplies,ElementProcess process) {
		HashMap<String, Element> elements = (HashMap<String, Element>) supplies;
		List<ElementBean> elementBeans = new ArrayList<ElementBean>();



		for (Object key : elements.keySet()) {

			Element element = elements.get(key);
			
			Integer n = element.getLevel();
			Integer lambda = element.getProductionIncrementRate();
			Integer p0= element.getIncrementable().getInitialValue();
			double increment=0.0;
		    
		
			
			logger.info("level :"+n+" lambda: "+lambda+" p0 "+p0);			

		
			increment = UtopiaUtil.calculateExpGrowth(p0, lambda, n);		
				
			element.setCalculatedValue((int)(0+increment));			
			
			Integer result = (int) (element.getQuantity() + increment);
			
			
			logger.info("P0 "+p0+" Increment "+increment+"  "+element.getIncrementable().getName()+" result "+result);
			
		    
		    element.setQuantity(result);

			ElementBean bean = new ElementBean();
			bean.setElement(element);
			
			bean.setProcess(process);
			elementBeans.add(bean);

		}

		return elementBeans;
	}

}
