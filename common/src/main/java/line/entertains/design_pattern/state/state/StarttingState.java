package line.entertains.design_pattern.state.state;

import line.entertains.design_pattern.state.context.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarttingState extends State {

	public StarttingState() {
		super(ContextState.STARTTING);
	}
	
	@Override
	public void action(Context context) throws StateChangeException {
		log.info("try context startting");
		
		if (!ContextState.STOPPED.equals(context.getState().getState()))
			throw new StateChangeException(context.getState().getState(), this.getState());
		
		context.setState(this);
	}

	@Override
	public String toString() {
		return getState().toString();
	}
}
