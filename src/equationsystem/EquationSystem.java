package equationsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import equationsystem.test.EquationBuilder;

public class EquationSystem {
	
	List<Equation> equations;
	SolutionMethod method;
	Map<String,Equation> solutionMap;
	
	public EquationSystem() {
		equations= new ArrayList<Equation>();
		solutionMap= new HashMap<String,Equation>();
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
		if ( idx < equations.size()) {
			return equations.get(idx);
		}
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
		solutionMap.put(firstName , equation);
	}
	
	public float getSolution(String name)
	{
		float solution = 0.0f;
		if (solutionMap.containsKey(name))
		{
			solution= solutionMap.get(name).getValue(Side.RIGHT);
		}
		return solution;
	}
	
	public boolean equal(EquationSystem equationSystem)
	{
		this.resolve();
		equationSystem.resolve();

		if (solutionMap.size() != equationSystem.solutionMap.size())
		{
			return false;
		}
		
		for (String name: solutionMap.keySet())
		{
			if ( !solutionMap.get(name).equal(equationSystem.solutionMap.get(name)) )
			{
				System.out.println(solutionMap.get(name) + "!="+ equationSystem.solutionMap.get(name));
				return false;
			}
		}
		return true;
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
	
	
	public static void main(String[] args) {
		EquationSystem system= new EquationSystem();

		Equation eq1= EquationBuilder.get("1x+1y= 3");
		Equation eq2= EquationBuilder.get("2x-1y=0");
		system.set(new EqualizationMethod());
		system.add(eq1);
		system.add(eq2);
		system.resolve();
		
		System.out.println("La solucion es: ");
		for (Equation eq: system.solutionMap.values())
		{
			System.out.println(eq);
		}
	}
}
