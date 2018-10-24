package line.entertains.common.bridged;

public class Child implements Parent<String> {

	@Override
	public void show(String t) {
		System.out.println(t.toString());
	}
}
