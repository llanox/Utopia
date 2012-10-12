package co.edu.udea.ludens.services.production;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.enums.EnumElementType;

@RunWith(MockitoJUnitRunner.class)
public class TestMaterialProducer {

	ProducerStrategy producer = new MaterialProducerWithGrowthRate();
	HashMap<String, Element> materials = new HashMap<String, Element>();

	@Before
	public void preparing() {
		Element m1 = new Element();
		Incrementable in1 = new Incrementable();
		in1.setName("M1");

		in1.setInitialValue(100);
		in1.setInitialUpgradingTime(2000);
		in1.setLevelIncrementDelayRate(15);
		in1.setProductionIncrementRate(15);
		in1.setType(EnumElementType.MATERIAL);

		m1.setIncrementable(in1);

		materials.put("M1", m1);

	}

	@Test
	public void testIT() throws InterruptedException {
		int i = 0;
		while (i <= 10) {
			materials.get("M1").setLevel(i);
			producer.produce(materials, null);

			Thread.sleep(1000);
			i++;
		}
	}
}
