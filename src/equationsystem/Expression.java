package equationsystem;

import java.util.HashSet;
import java.util.Set;

public class Expression {

	public Expression() {
		// TODO Auto-generated constructor stub
	}
	
	public Boolean empty()
	{
		return false;
	}

	public void add(Term term)
	{
		
	}
	
	public void add(Expression expression)
	{
		
	}
	
	public void multiply(float value)
	{
		
	}
	
	public void simplify(String name)
	{
		
	}
	
	public void simplify()
	{
		
	}
	
	public float getValue(String name)
	{
		return 0.0f;
	}
	
	public float getValue()
	{
		return 0.0f;
	}	
	
	public Set<String> getNameSet()
	{
		return new HashSet<String>();
	}
	
	public Boolean hasName(String name)
	{
		return false;
	}
	
	
	public void apply(String name , float value )
	{
		
	}
	
	public Boolean equal( Expression expression)
	{
		return false;
	}
	
	public Expression clon()
	{
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
