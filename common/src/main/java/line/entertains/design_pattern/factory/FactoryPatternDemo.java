package line.entertains.design_pattern.factory;

import line.entertains.design_pattern.state.context.Context;
import line.entertains.design_pattern.state.state.ContextState;
import line.entertains.design_pattern.state.state.State;

public class FactoryPatternDemo {

	public static void main(String[] args) {
		
		ContextStateFactory factory = new ContextStateFactory();
		
		Context context = new Context();
		
		State startting = factory.getState(ContextState.STARTTING);
		startting.action(context);
		
		State state = factory.getState(ContextState.RUNNING);
		state.action(context);
		
	}
}
