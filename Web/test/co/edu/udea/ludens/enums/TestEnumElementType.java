package co.edu.udea.ludens.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestEnumElementType {

	@Test
	public void testEnumElemenType() {
		EnumElementType type = EnumElementType.getElementType("Material");

		assertEquals("No es el enum solicitado", type, EnumElementType.MATERIAL);
	}
}
