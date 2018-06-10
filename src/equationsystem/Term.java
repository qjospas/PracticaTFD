package equationsystem;

import java.util.Set;

public abstract class Term {
	public Term(float value) {
	}
	
	public float getValue()
	{
		return 0.0f;
	}
	
	public void multiply(float value)
	{
		
	}
	
	public Boolean hasName(String name)
	{
		return false;
	}
	
	public Boolean hasName(Set<String> nameSet)
	{
		return false;
	}
	
	public Boolean equal(Term term)
	{
		return false;
	}
	
	public abstract Term clon();

	@Override
	public String toString()
	{
		return "";
	}
	
	public abstract void dispatch(TermVisitor termVisitor);
}
