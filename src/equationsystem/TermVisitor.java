package equationsystem;

public interface TermVisitor {
	public void visit( Variable var);
	public void visit( Constant con);
}
