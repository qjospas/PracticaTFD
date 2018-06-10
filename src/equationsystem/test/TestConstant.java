package equationsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import equationsystem.Constant;



public class TestConstant {

	@Test
	public void testEqual()
	{
		Constant a= new Constant( 1.0f );
		Constant b= new Constant( 1.0f );
		assertTrue( a.equal(b) );
	}
	
	@Test 
	public void TestClon()
	{
		Constant a= new Constant( (float) Math.PI );
		Constant b= (Constant)a.clon();
		assertTrue( a.equal(b) );
	}
	
	@Test
	public void TestMultiply()
	{
		Constant a= new Constant( 5.33f );
		a.multiply(2.0f);
		assertEquals( 10.66f , a.getValue(), 0.01f);
	}
}
