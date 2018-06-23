package equationsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquationSystem {
	
	List<Equation> equations;
	SolutionMethod method;
	
	public EquationSystem() {
		equations= new ArrayList<Equation>();
	}
	
	public void add( Equation equation )
	{
		equations.add(equation);
	}
	
	public void set( SolutionMethod solutionMethod)
	{
		method= solutionMethod;
	}
	
	public void resolve()
	{
		if ( method != null)
		{
			method.set(this);
			method.resolve();
		}
	}
	
	public Set<String> getNameSet()
	{
		Set<String> nameSet= new HashSet<String>();
		for( Equation equation: equations)
		{
			nameSet.addAll(equation.getNameSet());
		}
		return nameSet;
	}
	
	public Equation get(int idx)
	{
		return null;
		
	}
	
	public Equation getLast( int before)
	{
		return null;
		
	}
	
	public Equation getLast()
	{
		return null;
		
	}
	
	public void copyBefore(int before)
	{
		
	}
	
	public void copyBefore()
	{
		
	}
	
	public void setSolution(String firstName , Equation equation )
	{
		
	}
	
	public float getSolution(String name)
	{
		return 0.0f;
	}
	
	public boolean equal(EquationSystem equationSystem)
	{
		return false;
	}
	
	
	public String toString()
	{
		StringBuffer buffer= new StringBuffer();
		for( Equation equation: equations)
		{
			if(buffer.length() > 0) {
				buffer.append("\n");
			}
			buffer.append(equation.toString());
		}
		return buffer.toString();
	}
}
