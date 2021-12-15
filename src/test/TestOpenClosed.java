package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;



import org.junit.Before;
import org.junit.Test;

import openclosed.Calcul;
import openclosed.Cercle;
import openclosed.Rectangle;

public class TestOpenClosed {

	private Calcul calcul1, calcul2;
	
	@Before
	public void setUp() {
		calcul1 = new Calcul(new Cercle(4));
		calcul2 = new Calcul(new Rectangle(2, 3));
	}
	
	@Test
	public void test_airCercle_OK() {
		assertEquals(calcul1.calculAir(), Math.PI * 4 * 4, 0);
	}
	
	@Test
	public void test_airCercle_PasOK() {
		assertNotEquals(calcul1.calculAir(), Math.PI * 3 * 3, 0);
	}
	
	@Test
	public void test_airRectangle_OK() {
		assertTrue(calcul2.calculAir() == 6);
	}
	
	@Test
	public void test_airRectangle_PasOK() {
		assertFalse(calcul2.calculAir() == 4);
	}
}
