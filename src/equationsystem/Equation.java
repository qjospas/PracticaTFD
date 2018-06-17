package equationsystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Equation {
	
protected Map<Side , Expression > expressionsMap;
	
	public Equation() {
		expressionsMap= new HashMap() {
			{
				put ( Side.LEFT , new Expression() );
				put ( Side.RIGHT , new Expression() );
			}
		};
	}
	
	public Equation(Expression[] expressions) {
		expressionsMap= new HashMap() {
			{
				put ( Side.LEFT , expressions[0] );
				put ( Side.RIGHT , expressions[1] );
			}
		};
	}

	public void add(Side side ,Term term)
	{
		expressionsMap.get(side).add(term);
	}
	
	public void add(Term term)
	{
		for (Side side: expressionsMap.keySet())
		{
			add(side,term);
		}
	}
	
	public void add(Equation equation)
	{
		if ( equation == null )
		{
			return;
		}
		for (Side side: expressionsMap.keySet())
		{
			Expression expr= expressionsMap.get(side);
			expr.add(equation.expressionsMap.get(side));
			expressionsMap.put(side, expr);
		}
	}
	
	public void multiply(float value)
	{
		for (Side side: expressionsMap.keySet())
		{
			Expression expr= expressionsMap.get(side);
			expr.multiply(value);
		}
	}
	
	public void simplify(Side side , String name)
	{
		Expression expr= expressionsMap.get(side);
		expr.simplify(name);
	}
	
	public void simplify(Side side)
	{
		Expression expr= expressionsMap.get(side);
		expr.simplify();
	}
	
	public float getValue(String name)
	{
		for (Side side: expressionsMap.keySet())
		{
			Expression expr= expressionsMap.get(side);
			if ( expr.hasName(name) )
			{
				return expr.getValue(name);
			}
		}
		return 0.0f;
	}
	
	public float getValue(Side side)
	{

		Expression expr = expressionsMap.get(side);
		if (expr.hasName("")) {
			return expr.getValue("");
		}

		return 0.0f;
	}	
	
	public Set<String> getNameSet()
	{
		HashSet<String> nameSet = new HashSet<String>();
		nameSet.addAll(expressionsMap.get(Side.LEFT).getNameSet());
		nameSet.addAll(expressionsMap.get(Side.RIGHT).getNameSet());
		return nameSet;
	}
	
	
	public void apply(String name , float value )
	{
		for (Side side: expressionsMap.keySet())
		{
			Expression expr= expressionsMap.get(side);
			expr.apply(name, value);
		}
	}
	
	public void invert()
	{
		Expression left= expressionsMap.get(Side.LEFT);
		Expression right= expressionsMap.get(Side.RIGHT);
		expressionsMap.put(Side.LEFT, right);
		expressionsMap.put(Side.RIGHT, left);
	}
	
	public Boolean equal( Equation equation)
	{
		if ( equation == null )
		{
			return false;
		}
		return (expressionsMap.get(Side.LEFT ).equal(equation.expressionsMap.get(Side.LEFT))
				&&
				expressionsMap.get(Side.RIGHT ).equal(equation.expressionsMap.get(Side.RIGHT)));
	}
	
	public Equation clon()
	{
		Expression[] expressions = new Expression[] {
				expressionsMap.get(Side.LEFT).clon(),
				expressionsMap.get(Side.RIGHT).clon()
				
		};
		Equation eq= new Equation(expressions);
		return eq;
		
	}
	
	@Override
	public String toString() {
		StringBuffer buffer= new StringBuffer();
		buffer.append(expressionsMap.get(Side.LEFT));
		buffer.append("=");
		buffer.append(expressionsMap.get(Side.RIGHT));
		return buffer.toString();
	}
}
