package co.edu.udea.ludens.services.production;

import java.util.List;

import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.web.ElementBean;

public interface ProducerStrategy {

	public List<ElementBean> produce(Object supplies,
			ElementProcess playerProcess);
}
