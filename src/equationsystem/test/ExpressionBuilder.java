package equationsystem.test;

import java.util.Arrays;

import equationsystem.Constant;
import equationsystem.Expression;
import equationsystem.Variable;

public class ExpressionBuilder {

	public static Expression get(String expr) {
		expr= expr.replaceAll("\\s+", "");
		Expression expression= new Expression();
		String[] words = expr.split("(?=[+-])");
		for (String word: words)
		{
			String[] term = word.split("(?=[a-zA-Z])");
			String value= term[0];
			if ( term.length > 1)
			{
				String nameArray[] = Arrays.copyOfRange(term, 1, term.length);
				String name= String.join("", nameArray);
				expression.add(new Variable(Float.parseFloat(value),name));
			}else
			{
				expression.add(new Constant(Float.parseFloat(value)));
			}
				
		}
		return expression;
	}
	
	public static void main(String[] args) {
		Expression a = ExpressionBuilder.get("3.5x-5Y+33abc+123");
		System.out.println(a);
	}
}
