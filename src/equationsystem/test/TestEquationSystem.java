package equationsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import equationsystem.EqualizationMethod;
import equationsystem.Equation;
import equationsystem.EquationSystem;

public class TestEquationSystem {
	
	@Test
	public void testAdd() {
		EquationSystem system= new EquationSystem();
		Equation eq1= EquationBuilder.get("-3x +5y = +23");
		Equation eq2= EquationBuilder.get("+6x -8y = +21");
		system.add(eq1);
		system.add(eq2);
		assertEquals("-3,00x+5,00y=+23,00\n" + 
				     "+6,00x-8,00y=+21,00", system.toString());
	}
	
	@Test
	public void testGetNameSet() {
		Set<String> names= new HashSet<String>( Arrays.asList("x","y") );
		EquationSystem system= new EquationSystem();
		Equation eq1= EquationBuilder.get("-3x +5y = +23");
		Equation eq2= EquationBuilder.get("+6x -8y = +21");
		system.add(eq1);
		system.add(eq2);
		assertEquals(names, system.getNameSet());
	}
	
	@Test
	public void testResolve() {
		EquationSystem system= new EquationSystem();

		Equation eq1= EquationBuilder.get("-3x +5y = +23");
		Equation eq2= EquationBuilder.get("+6x -8y = +21");
		system.set(new EqualizationMethod());
		system.add(eq1);
		system.add(eq2);
		system.resolve();
		assertEquals( 48.16f , system.getSolution("x"), 0.01f);
		assertEquals( 33.50f , system.getSolution("y"), 0.01f);
	}
	
	@Test
	public void testEqual() {
		EquationSystem system1= new EquationSystem();
		EquationSystem system2= new EquationSystem();
		Equation eq1= EquationBuilder.get("-3x +5y = +23");
		Equation eq2= EquationBuilder.get("+6x -8y = +21");
		Equation eq3= EquationBuilder.get("-3x +5y -23 = 0");
		Equation eq4= EquationBuilder.get("-8y = +21 -6x");
		system1.set(new EqualizationMethod());
		system2.set(new EqualizationMethod());
		
		system1.add(eq1);
		system1.add(eq2);
		
		system2.add(eq4);
		system2.add(eq3);
	
		assertTrue( system1.equal(system2) );

	}
	
}
