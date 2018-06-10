package equationsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import equationsystem.Constant;
import equationsystem.Expression;
import equationsystem.Variable;

public class TestExpression {

	@Test
	public void testEmpty()
	{
		Expression expr= new Expression();
		assertTrue( expr.empty() );
	}
	
	@Test
	public void testAdd()
	{
		Expression expr= new Expression();
		Variable a= new Variable(3.0f, "x");
		expr.add(a);
		assertFalse( expr.empty() );
		assertEquals( 3.0f ,expr.getValue("x"), 0.01f );
	}
	
	@Test
	public void testAddExpressionGetVariable()
	{
		Expression expr1= new Expression();
		Expression expr2= new Expression();
		Constant c= new Constant(3.0f);
		Variable v= new Variable(5.0f , "a");
		expr1.add(v);
		expr2.add(c);
		expr2.add(expr1);

		assertEquals( 5.0f ,expr2.getValue("a"), 0.01f );
	}
	
	@Test
	public void testAddExpressionGetConstant()
	{
		Expression expr1= new Expression();
		Expression expr2= new Expression();
		Constant c= new Constant(3.0f);
		Variable v= new Variable(5.0f , "a");
		expr1.add(v);
		expr2.add(c);
		expr2.add(expr1);

		assertEquals( 3.0f ,expr2.getValue(), 0.01f );
	}
	
	public void testMultiply()
	{
		Expression expr1= new Expression();
		Expression expr2= new Expression();
		Constant c1= new Constant(3.0f);
		Constant c2= new Constant(15.0f);
		Variable v1= new Variable(5.0f , "a");
		Variable v2= new Variable(25.0f , "a");
		expr1.add(v1);
		expr1.add(c1);
		expr2.add(v2);
		expr2.add(c2);
		
		expr1.multiply(5.0f);
		
		assertTrue(  expr1.equal(expr2) );
	}
	
	@Test
	public void testSimplify()
	{
		List<Float> valueList1 = Arrays.asList(27.1f, 10.0f, 14.5f, 5.67f);
		Set<String> nameSet1 = new HashSet<>(Arrays.asList("a", "b", "c" , "b"));
		List<Float> valueList2 = Arrays.asList(27.1f, 15.67f, 14.5f);
		Set<String> nameSet2 = new HashSet<>(Arrays.asList("a", "b", "c"));
		Expression expr1= new Expression();
		Expression expr2= new Expression();
		
		int idx= 0;
		for (String name: nameSet1)
		{
			Variable v= new Variable(valueList1.get(idx) , name);
			expr1.add(v);
			idx++;
		}
		
		idx= 0;
		for (String name: nameSet2)
		{
			Variable v= new Variable(valueList2.get(idx) , name);
			expr2.add(v);
			idx++;
		}
		
		expr1.simplify("a");
		
		assertFalse( expr2.equal(expr2) );
		
		expr1.simplify("b");
		
		assertTrue( expr2.equal(expr2) );
	}
	
	
	@Test
	public void testGetNameSpace()
	{
		Set<String> nameSet = new HashSet<>(Arrays.asList("a", "b", "c"));
		Expression expr1= new Expression();
		Constant c= new Constant(3.0f);
		Variable v1= new Variable(5.0f , "a");
		Variable v2= new Variable(5.0f , "b");
		Variable v3= new Variable(5.0f , "c");
		expr1.add(c);
		expr1.add(v1);
		expr1.add(v2);
		expr1.add(v3);
		
		assertEquals( nameSet ,expr1.getNameSet() );
	}
	
	@Test
	public void testHasName()
	{
		List<String> names= Arrays.asList("a", "b", "c");
		Expression expr= new Expression();

		for (String name: names)
		{
			Variable v= new Variable(5.0f , name);
			expr.add(v);
		}
		
		for (String name: names)
		{
			assertTrue( expr.hasName(name) );
		}
	}
	
	@Test
	public void testSimpleApply()
	{
		Expression expr= new Expression();
		Variable v= new Variable(5.0f , "a");
		expr.add(v);
        expr.apply("a" , 2.0f );

		assertEquals( 10.0f ,expr.getValue("a"), 0.01f );
	}
}
