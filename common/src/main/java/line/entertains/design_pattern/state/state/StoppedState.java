package line.entertains.design_pattern.state.state;

import line.entertains.design_pattern.state.context.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StoppedState extends State {

	public StoppedState() {
		super(ContextState.STOPPED);
	}
	
	@Override
	public void action(Context context) {
		log.info("try context stopped");
		context.setState(this);
	}

	@Override
	public String toString() {
		return getState().toString();
	}
}
