package equationsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import equationsystem.Variable;

public class TestVariable {

	@Test
	public void testHasName()
	{
		Variable a = new Variable( 3.0f , "x" );
		assertTrue( a.hasName("x") );		
	}
	
	@Test
	public void testHasNameSet()
	{
		Variable a = new Variable( 3.0f , "x" );
		Set<String> names= new HashSet<String>();
		names.add("a");
		names.add("b");
		names.add("x");
		assertTrue( a.hasName("x") );	
	}
	
	@Test
	public void testNotHasName()
	{
		Variable a = new Variable( 3.0f , "x" );
		assertFalse( a.hasName("y") );		
	}
	
	@Test
	public void testNotHasNameSet()
	{
		Variable a = new Variable( 3.0f , "x" );
		Set<String> names= new HashSet<String>();
		names.add("a");
		names.add("b");
		names.add("x");
		assertFalse( a.hasName("y") );	
	}

}
