package equationsystem;

import java.util.Set;

public class Variable extends Term {

	protected String name;
	public Variable(float value , String name) {
		super(value);
		this.name= name;
	}
 
	public String getName()
	{
		return this.name;
	}

	@Override
	public Boolean equal(Term obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if ( !( super.equal(obj) ) )
		{
			return false;
		}
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	
	
	@Override
	public Term clon() {
		return new Variable( this.value , this.name );
	}
	
	@Override
	public Boolean hasName(String name) {
		return this.name.equals(name);
	}
	
	@Override
	public Boolean hasName(Set<String> nameSet) {
		if ( nameSet == null)
		{
			return false;
		}
		for ( String name: nameSet)
		{
			if ( hasName(name) )
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void dispatch(TermVisitor termVisitor) {
		termVisitor.visit(this);
	}
	
    @Override
    public String toString() {
    	return super.toString()+this.name;
    }

}
