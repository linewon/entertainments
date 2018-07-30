package line.entertains.design_pattern.state.state;

import line.entertains.design_pattern.state.context.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class State {

	@Getter
	protected ContextState state;
	
	public abstract void action(Context context) throws StateChangeException;
}
