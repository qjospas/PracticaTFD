package equationsystem;

import java.util.Set;

public abstract class Term {
	protected float value;
	
	public Term(float value) {
		this.value= value;
	}
	
	public float getValue()
	{
		return value;
	}
	
	public void multiply(float value)
	{
		this.value*= value;
	}
	
	public Boolean hasName(String name)
	{
		return name.isEmpty();
	}
	
	public Boolean hasName(Set<String> nameSet)
	{
		return false;
	}
	
	public Boolean equal(Term term)
	{
		if ( term != null) 
		{
			return this.value == term.value;
		}
		return false;
	}
	
	public abstract Term clon();

	@Override
	public String toString()
	{
		return String.format("%+.2f", this.value);
	}
	
	public abstract void dispatch(TermVisitor termVisitor);
}
