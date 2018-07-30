package line.entertains.design_pattern.state.context;

import line.entertains.design_pattern.state.state.State;
import line.entertains.design_pattern.state.state.StoppedState;
import lombok.Getter;
import lombok.Setter;

public class Context {
	
	public Context() {
		state = new StoppedState();
	}

	@Getter
	@Setter
	private State state;
}
