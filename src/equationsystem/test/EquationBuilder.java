package equationsystem.test;

import equationsystem.Equation;
import equationsystem.Expression;

public class EquationBuilder {

	public static Equation get(String equation)
	{
		
		String[] expressions = equation.split("=");
		Expression ex1= ExpressionBuilder.get(expressions[0]);
		Expression ex2= ExpressionBuilder.get(expressions[1]);
		Expression[] expressions1= new Expression[] {ex1 ,ex2};
		Equation eq= new Equation(expressions1);
		return eq;
	}
	
	public static void main(String[] args) {
		Equation a = EquationBuilder.get("10a+5a=40.0-10.0");
		System.out.println(a);
	}
}
