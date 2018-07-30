package line.entertains.design_pattern.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Item {

	protected String name;
	protected Integer quantity;
	
	public abstract void buyIn();
	public abstract void sellOut();
}
