package equationsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Expression {
	
	protected List<Term> terms;

	public Expression() {
		terms= new ArrayList<Term>();
	}
	
	public Boolean empty()
	{
		return terms.isEmpty();
	}

	public void add(Term term)
	{
		if ( term != null )
		{
			terms.add(term);
		}
	}
	
	public void add(Expression expression)
	{
		if ( expression != null )
		{
			for( Term term:expression.terms)
			{
				terms.add(term.clon());
			}
		}
	}
	
	public void multiply(float value)
	{
		for (Term term: terms)
		{
			term.multiply(value);
		}
	}
	public void simplify(String name)
	{
		simplifyFactor(name , 1.0f);
	}
	
	public void simplifyFactor(String name, float factor)
	{
		List<Term> newTerms= new ArrayList<Term>();
		float newValue= 0.0f;
		boolean found = false;
		boolean hasVariables = false;
		
		for (Term term: this.terms)
		{
			
			//System.out.println("Testing '" + name + "' in " + term.toString());
			if ( term.hasName(name) )
			{
				//System.out.println("Found");
				newValue+= term.getValue();
				found= true;
			}
			else
			{
				newTerms.add(term);
			
					hasVariables= true;
				
			}
		}
		if ( found )
		{
			Term newTerm;
			if ( !(newValue == 0.0f && hasVariables  ) )
			{
				if (factor == 1.0f && !name.isEmpty()) {

					newTerm = new Variable(newValue, name);

				} else {
					newTerm = new Constant(newValue * factor);
				}
				//System.out.println("Adding '" + newTerm.toString() + "'");
				newTerms.add(newTerm);
			}

		}
		this.terms= newTerms;
	}
	
	public void simplify()
	{
		simplify("");
	}
	
	public float getValue(String name)
	{
		for ( Term term: this.terms)
		{
			if ( term.hasName(name))
			{
				return term.getValue();
			}
		}
		return 0.0f;
	}
	
	public float getValue()
	{
		for ( Term term: this.terms)
		{
			if ( term.hasName(""))
			{
				return term.getValue();
			}
		}
		return 0.0f;
	}	
	
	public Set<String> getNameSet()
	{
		return new NamesExpresionAnalyzer(this.terms).getNameSet();
	}
	
	public Boolean hasName(String name)
	{
		return getNameSet().contains(name);
	}
	
	
	public void apply(String name , float value )
	{
		simplifyFactor(name, value);
	}
	
	
	
	
	public boolean equal(Expression expression) {
		if (this == expression)
			return true;
		if (expression == null)
			return false;
		if (terms == null) {
			if (expression.terms != null)
				return false;
		} 
		Set<String> nameSet= new HashSet<String>();
		nameSet.addAll(getNameSet());
		nameSet.addAll(expression.getNameSet());
		for ( String name: nameSet )
		{
			if ( !expression.hasName(name))
			{
				return false;
			}
			if ( getValue(name)!=expression.getValue(name) ) 
			{
				return false;
			}
		}
		return true;
	}

	
	public Expression clon()
	{
		Expression expr= new Expression();
		expr.add(this);
		return expr;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer= new StringBuffer();
		for ( Term term: this.terms)
		{
			buffer.append(term.toString());
		}
		return buffer.toString();
	}
}
