package equationsystem;

public class Variable extends Term {

	public Variable(float value , String name) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean equal(Term term) {
		return super.equal(term);
	}	
	
	@Override
	public Term clon() {
		return null;
	}
	
	@Override
	public Boolean hasName(String name) {
		return super.hasName(name);
	}

	@Override
	public void dispatch(TermVisitor termVisitor) {
	}

}
