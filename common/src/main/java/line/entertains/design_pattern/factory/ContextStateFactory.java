package line.entertains.design_pattern.factory;

import line.entertains.design_pattern.state.state.ContextState;
import line.entertains.design_pattern.state.state.RunningState;
import line.entertains.design_pattern.state.state.StarttingState;
import line.entertains.design_pattern.state.state.State;
import line.entertains.design_pattern.state.state.StoppedState;
import line.entertains.design_pattern.state.state.StoppingState;

public class ContextStateFactory {

	public State getState(ContextState contextState) {
		
		State result = null;
		switch (contextState) {
		case STOPPED:
			result = new StoppedState();
			break;
		case STARTTING:
			result = new StarttingState();
			break;
		case RUNNING:
			result = new RunningState();
			break;
		case STOPPING:
			result = new StoppingState();
			break;
		default:
			break;
		}
		return result;
	}
}
