package equationsystem;

public class Constant extends Term {

	
	public Constant(float value) {
		super(value);
	}
	
	@Override
	public Boolean equal(Term term) {
		return super.equal(term);
	}
	
	@Override
	public Term clon() {
		return new Constant(this.value);
	}

	@Override
	public void dispatch(TermVisitor termVisitor) {
		termVisitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
