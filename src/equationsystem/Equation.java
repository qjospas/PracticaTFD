package equationsystem;

import java.util.HashSet;
import java.util.Set;

public class Equation {
	
	public Equation() {
		// TODO Auto-generated constructor stub
	}
	
	public Equation(Expression[] expressions) {
	}

	public void add(Side side ,Term term)
	{
		
	}
	
	public void add(Term term)
	{
		
	}
	
	public void add(Equation equation)
	{
		
	}
	
	public void multiply(float value)
	{
		
	}
	
	public void simplify(Side side , String name)
	{
		
	}
	
	public void simplify(Side side)
	{
		
	}
	
	public float getValue(String name)
	{
		return 0.0f;
	}
	
	public float getValue(Side side)
	{
		return 0.0f;
	}	
	
	public Set<String> getNameSet()
	{
		return new HashSet<String>();
	}
	
	
	public void apply(String name , float value )
	{
		
	}
	
	public void invert()
	{
		
	}
	
	public Boolean equal( Equation equation)
	{
		return false;
	}
	
	public Equation clon()
	{
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
