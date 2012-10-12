package co.edu.udea.ludens.services.production;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.util.UtopiaUtil;
import co.edu.udea.ludens.web.ElementBean;

public class PopulationProducerWithGrowthRate implements ProducerStrategy {

	public static final Logger logger = Logger
			.getLogger(PopulationProducerWithGrowthRate.class);

	@Override
	public List<ElementBean> produce(Object supplies, ElementProcess process) {

		List<ElementBean> elementBeans = new ArrayList<ElementBean>();
		Element population = (Element) supplies;

		Integer n = UtopiaUtil.calculateTimeN(process.getPlayer());
		Integer lambda = population.getProductionIncrementRate();
		Integer p0 = population.getIncrementable().getInitialValue();
		Double increment = 0.0;

		logger.info("n " + n + " lambda " + lambda + " p0 " + p0);
		increment = UtopiaUtil.calculateExpGrowth(p0, lambda, n);

		int result = (int) (increment + 0);
		logger.info("P0 " + p0 + " Old value " + population.getQuantity()
				+ " Increment " + increment + "  "
				+ population.getIncrementable().getName() + " result " + result);

		population.setQuantity(result);

		ElementBean bean = new ElementBean();
		bean.setElement(population);
		bean.setProduction(0);
		bean.setProcess(process);

		elementBeans.add(bean);

		return elementBeans;
	}
}
