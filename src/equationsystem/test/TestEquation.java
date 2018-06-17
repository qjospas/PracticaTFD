package equationsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import equationsystem.Constant;
import equationsystem.Equation;
import equationsystem.Expression;
import equationsystem.Side;
import equationsystem.Variable;

public class TestEquation {

	@Test
	public void constructorTest()
	{
		Expression expr1= ExpressionBuilder.get("-25.0x+13.6y+123");
		Expression expr2= ExpressionBuilder.get("7x+8y");
		Expression[] expressions1= new Expression[] {expr1 ,expr2};
		Equation eq1= new Equation(expressions1);
		
		Equation eq2= EquationBuilder.get("-25.0x+13.6y+123=7x+8y");
		
		assertTrue(eq1.equal(eq2));
	}
	
	@Test
	public void addSideTermTest()
	{
		Equation eq1= EquationBuilder.get("10=5a");
		Equation eq2= EquationBuilder.get("5a+10=5a+10");
		
		Variable v= new Variable(5.0f , "a");
		Constant c= new Constant(10.0f);
		eq1.add(Side.LEFT, v);
		eq1.add(Side.RIGHT, c);
		
		assertTrue(eq2.equal(eq1));
	}
	
	
	@Test
	public void addTermTest()
	{
		Equation eq1= EquationBuilder.get("3x=3x");
		Equation eq2= EquationBuilder.get("3x+5a=3x+5a");
		Variable v= new Variable(5.0f , "a");
		
		eq1.add(v);

		assertTrue(eq2.equal(eq1));
	}
	
	@Test
	public void addEquationTest()
	{
		Equation eq1= EquationBuilder.get("2x=3");
		Equation eq2= EquationBuilder.get("3x+5a=3x+5a");
		Equation eq3= EquationBuilder.get("2x+3x+5a=3+3x+5a");
		eq1.add(eq2);

		assertTrue(eq1.equal(eq3));
	}
	
	@Test
	public void applyTest()
	{
		Equation eq1 = EquationBuilder.get("5a=10");
		Equation eq2 = EquationBuilder.get("10=10");
		
		eq1.apply("a", 2.0f);
		
		assertTrue(eq1.equal(eq2));
	}
	
	@Test
	public void invertTest()
	{
		Equation eq1 = EquationBuilder.get("5a+3b=7x+2y");
		Equation eq2 = EquationBuilder.get("7x+2y=5a+3b");
		
		eq1.invert();
		
		assertTrue(eq1.equal(eq2));
	}
	
	@Test
	public void clonTest()
	{
		Equation eq1= EquationBuilder.get("-25.0x+13.6y+123=7x+8y");
		
		Equation eq2 = eq1.clon();
		
		assertTrue(eq1.equal(eq2));
	}
	
	@Test
	public void getNameSetTest()
	{
		Set<String> nameSet1 = new HashSet<>(Arrays.asList("x", "y" , "abc"));
		Equation eq1= EquationBuilder.get("-25.0x+13.6y+123=7x+8abc");
		
		Set<String> nameSet2= eq1.getNameSet();
		
		assertEquals(nameSet1, nameSet2);
	}
	
	@Test
	public void multiplyTest()
	{
		Equation eq1 = EquationBuilder.get("5a=3b");

		eq1.multiply(3.0f);
		
		assertEquals(15.0f, eq1.getValue("a") , 0.01f);
		assertEquals(9.0f, eq1.getValue("b") , 0.01f);
	}
	
	@Test
	public void simplifyTest()
	{
		Equation eq1 = EquationBuilder.get("10a+5a=40.0-10.0");

		eq1.simplify(Side.LEFT , "a");
		eq1.simplify(Side.RIGHT);
		
		assertEquals(15.0f, eq1.getValue("a") , 0.01f);
		assertEquals(30.0f, eq1.getValue(Side.RIGHT) , 0.01f);
	}
	
	@Test
	public void toStringTest()
	{
		Equation eq1 = EquationBuilder.get("10a+5a=40.0-10.0");

		assertEquals("+10,00a+5,00a=+40,00-10,00", eq1.toString());
	}
}
