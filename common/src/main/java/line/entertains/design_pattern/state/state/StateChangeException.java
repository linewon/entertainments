package line.entertains.design_pattern.state.state;


public class StateChangeException extends RuntimeException {

	private static final long serialVersionUID = -2839302780553566446L;
	
//	private ContextState preState;
//	private ContextState nextState;
	
	public StateChangeException(ContextState pre, ContextState next) {
		super(pre + " connot be transformed to " + next);
		
//		this.preState = pre;
//		this.nextState = next;
	}
}
