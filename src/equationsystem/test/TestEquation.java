package equationsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
		Expression expr1= new Expression();
		Expression expr2= new Expression();
		Variable v= new Variable(5.0f , "a");
		Constant c= new Constant(10.0f);
		expr1.add(v);
		expr2.add(c);
		Expression[] expressions1= new Expression[] {expr1 ,expr2};
		Expression[] expressions2= new Expression[] {expr1.clon() ,expr2.clon()};
		Equation eq1= new Equation(expressions1);
		Equation eq2= new Equation(expressions2);
		assertTrue(eq1.equal(eq2));
	}
	
	@Test
	public void addSideTermTest()
	{
		Equation eq1= new Equation();
		Variable v= new Variable(5.0f , "a");
		Constant c= new Constant(10.0f);
		eq1.add(Side.LEFT, v);
		eq1.add(Side.RIGHT, v);
		
		Expression expr1= new Expression();
		Expression expr2= new Expression();
		expr1.add(v);
		expr2.add(c);
		Expression[] expressions1= new Expression[] {expr1 ,expr2};
		Equation eq2 = new Equation(expressions1);
		assertTrue(eq2.equal(eq1));
	}
}
