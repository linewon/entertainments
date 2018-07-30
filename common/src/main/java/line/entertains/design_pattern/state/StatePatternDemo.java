package line.entertains.design_pattern.state;

import line.entertains.design_pattern.state.context.Context;
import line.entertains.design_pattern.state.state.RunningState;
import line.entertains.design_pattern.state.state.StarttingState;
import line.entertains.design_pattern.state.state.StateChangeException;
import line.entertains.design_pattern.state.state.StoppedState;
import line.entertains.design_pattern.state.state.StoppingState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatePatternDemo {

	public static void main(String[] args) {
		Context context = new Context();
		log.info("current state: {}", context.getState());

		try {
			new StarttingState().action(context);
			log.info("current state: {}", context.getState());

			new RunningState().action(context);
			log.info("current state: {}", context.getState());

			new StoppedState().action(context);
			log.info("current state: {}", context.getState());
			
			new StoppingState().action(context);
			log.info("current state: {}", context.getState());
		} catch (StateChangeException e) {
			log.error("STATE EXCEPTION!!", e);
		}
	}
}
