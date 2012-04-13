package co.edu.udea.ludens.services.production;

import co.edu.udea.ludens.enums.EnumElementType;

public class ProducerFactory {

	
	public static ProducerStrategy createProducer(EnumElementType productType) {

		if (EnumElementType.FACTOR == productType) {
			return new FactorProducerWithGrowthRate();
		}

		if (EnumElementType.MATERIAL == productType) {
			return new MaterialProducerWithGrowthRate();
		}

		if (EnumElementType.POPULATION == productType) {
			return new PopulationProducerWithGrowthRate();
		}

		return null;
	}
	
	
	
}
