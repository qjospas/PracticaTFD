package equationsystem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NamesExpresionAnalyzer implements TermVisitor {
	
	private Set<String> nameSet;
	
	public NamesExpresionAnalyzer(List<Term> terms) {
		this.nameSet= new HashSet<String>();
		for(Term term: terms)
		{
			term.dispatch(this);
		}
	}

	@Override
	public void visit(Variable var) {
		nameSet.add(var.getName());
	}

	@Override
	public void visit(Constant con) {
		//Do nothing
	}
	
	public Set<String> getNameSet()
	{
		return nameSet;
	}

}
