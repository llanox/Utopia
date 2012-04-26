package co.edu.udea.ludens.services.production;

import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.services.production.FactorProducerWithGrowthRate;
import co.edu.udea.ludens.services.production.ProducerStrategy;


@RunWith(MockitoJUnitRunner.class)
public class TestFactorProducer {
	
	ProducerStrategy producer = new FactorProducerWithGrowthRate();
	HashMap<String, Element> factors = new HashMap<String, Element>();
	
	@Mock
	private ElementProcess elementProcess;
	
	private Player player = new Player();
	private Element population =  new Element();

	@Before
	public void preparing() {
		Element m1 = new Element();
		Incrementable in1 = new Incrementable();
		in1.setName("F1");
		
		in1.setInitialValue(100);
		in1.setInitialUpgradingTime(2000);
		in1.setLevelIncrementDelayRate(13);
		in1.setProductionIncrementRate(13);
		in1.setType(EnumElementType.FACTOR);

		m1.setIncrementable(in1);
		m1.setLevel(1);
		factors.put("F1", m1);
		population.setQuantity(67);
		
		player.setPopulation(population);		
		when(elementProcess.getPlayer()).thenReturn(player);

	}

	@Test
	public void testIT() throws InterruptedException {

		int i = 0;
		while (i <= 10) {
			
			producer.produce(factors, elementProcess);			
			Thread.sleep(1000);
			i++;
		}
	}

}
