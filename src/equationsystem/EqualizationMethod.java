package equationsystem;

import java.util.Iterator;
import java.util.Set;

import equationsystem.EquationSystem;
import equationsystem.SolutionMethod;

public class EqualizationMethod implements SolutionMethod {

	EquationSystem system;
	
	@Override
	public void set(EquationSystem system) {
	 this.system=system;
	}

	@Override
	public void resolve() {
		Set<String> names = system.getNameSet();
			
		if (names.size() == 2 )
		{
			EquationSystem partSystem= new EquationSystem();
			partSystem.add(system.get(0).clon());
			partSystem.add(system.get(1).clon());
			Iterator<String> it = names.iterator();
			String firstName = it.next();
			String secondName = it.next();
			System.out.println(partSystem);
			System.out.println("----------------");
			EquationSystem tmpSystem = moveConstantsToRight(partSystem);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= isolateVariable(tmpSystem, firstName);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= makeEqual(tmpSystem, firstName);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= equalize(tmpSystem, firstName);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= moveConstantsToRight(tmpSystem);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= isolateVariable(tmpSystem,secondName);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= makeSingular(tmpSystem,secondName);
		
			System.out.println(tmpSystem);
			System.out.println("================");
			this.system.setSolution(secondName, tmpSystem.get(0));
			partSystem= new EquationSystem();
			Equation eq = system.get(0).clon();
			eq.apply(secondName,tmpSystem.get(0).getValue(Side.RIGHT));
			partSystem.add(eq);
			System.out.println(partSystem);
			System.out.println("----------------");
			tmpSystem = moveConstantsToRight(partSystem);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= isolateVariable(tmpSystem, firstName);
			System.out.println(tmpSystem);
			System.out.println("----------------");
			tmpSystem= makeSingular(tmpSystem,firstName);
			System.out.println(tmpSystem);
			System.out.println("================\n\n");
			this.system.setSolution(firstName, tmpSystem.get(0));
		}
		

	}

	private EquationSystem moveConstantsToRight(EquationSystem system)
	{
		EquationSystem newSystem= new EquationSystem();
		for ( Equation equation: system.equations)
		{
			equation.simplify(Side.LEFT);
			float value= equation.getValue(Side.LEFT);
			Equation newEq= equation.clon();
			newEq.add(new Constant(-value));
			newEq.simplify(Side.LEFT);
			newEq.simplify(Side.RIGHT);
			newSystem.add(newEq);
		}
		return newSystem;
	}
	
	
	
	private EquationSystem isolateVariable(EquationSystem system , String name)
	{
		EquationSystem newSystem= new EquationSystem();
		for ( Equation equation: system.equations)
		{
			Equation newEq= equation.clon();
			for (String otherName: equation.getNameSet() )
			{
				// System.out.println("["+otherName+"]");
				if (name.equals(otherName)) {
					float value= equation.getValue(Side.RIGHT, otherName);
					if ( value != 0.0f)
					{
						newEq.add(new Variable(-value, otherName));
						newEq.simplify(Side.LEFT, otherName);
						newEq.simplify(Side.RIGHT, otherName);
					}

				} else {
					equation.simplify(Side.LEFT, otherName);
					float value = equation.getValue(otherName);

					newEq.add(new Variable(-value, otherName));
					newEq.simplify(Side.LEFT, otherName);
					newEq.simplify(Side.RIGHT, otherName);
				}

			}
			newEq.simplify(Side.LEFT);
			newEq.simplify(Side.RIGHT);
			newSystem.add(newEq);
		}
		return newSystem;
	}
	private EquationSystem makeEqual(EquationSystem system, String name)
	{
		EquationSystem newSystem= new EquationSystem();

		float val1 = system.get(0).getValue(name);
		float val2 = system.get(1).getValue(name);
		float[] vals=new float[]{val2, val1};
		
		for ( int idx=0; idx < 2 ; idx++)
		{
			Equation newEq= system.get(idx).clon();

			newEq.multiply(vals[idx]);
			newEq.simplify(Side.LEFT,name);
			newEq.simplify(Side.RIGHT,name);
			newEq.simplify(Side.LEFT);
			newEq.simplify(Side.RIGHT);
			newSystem.add(newEq);
		}
		return newSystem;
	}
	private EquationSystem makeSingular(EquationSystem system, String name)
	{
		EquationSystem newSystem= new EquationSystem();
		for ( Equation equation: system.equations)
		{
			Equation newEq= equation.clon();

			equation.simplify(Side.LEFT,name);
			float value= equation.getValue(name);
			newEq.multiply(1/value);
			newEq.simplify(Side.LEFT,name);
			newEq.simplify(Side.RIGHT,name);
			newEq.simplify(Side.LEFT);
			newEq.simplify(Side.RIGHT);
			newSystem.add(newEq);
		}
		return newSystem;
	}

	private EquationSystem equalize(EquationSystem system, String name)
	{
		EquationSystem newSystem= new EquationSystem();
		Equation first = system.equations.get(0).clon();
		first.invert();
		Equation second = system.equations.get(1).clon();
		first.add(second);
		first.apply(name, 0.0f);
		first.simplify(Side.LEFT,name);
		first.simplify(Side.RIGHT,name);
		first.simplify(Side.LEFT);
		first.simplify(Side.RIGHT);
		newSystem.add(first);
		return newSystem;
	}
	
	
}
