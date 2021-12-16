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

	private Cercle c;
	private Rectangle r;
	
	@Before
	public void setUp() {
		c = new Cercle(4);
		r = new Rectangle(2, 3);
	}
	
	@Test
	public void test_airCercle_OK() {
		assertEquals(new Calcul().calculAir(c), Math.PI * 4 * 4, 0);
	}
	
	@Test
	public void test_airCercle_PasOK() {
		assertNotEquals(new Calcul().calculAir(c), Math.PI * 3 * 3, 0);
	}
	
	@Test
	public void test_airRectangle_OK() {
		assertTrue(new Calcul().calculAir(r) == 6);
	}
	
	@Test
	public void test_airRectangle_PasOK() {
		assertFalse(new Calcul().calculAir(r) == 4);
	}
}
