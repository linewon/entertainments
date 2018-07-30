package line.entertains.design_pattern.state.state;

import line.entertains.design_pattern.state.context.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StoppingState extends State {
	
	public StoppingState() {
		super(ContextState.STOPPING);
	}

	@Override
	public void action(Context context) {
		log.info("try context stopping");
		
		if (ContextState.STOPPED.equals(context.getState().getState()))
			throw new StateChangeException(context.getState().getState(), this.getState());
		
		context.setState(this);
	}

	@Override
	public String toString() {
		return getState().toString();
	}
}
