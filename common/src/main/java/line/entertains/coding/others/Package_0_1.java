package line.entertains.coding.others;

public class Package_0_1 {

	private static int[] values = null;

	private static int[] weights = null;

	public static int pack(int maxWeight, int[] values, int[] weights) {
		Package_0_1.values = values;
		Package_0_1.weights = weights;

		return finish(values.length, maxWeight);

	}

	/*
	 * 这个函数就是状态转移方程。
	 * 写成递归的形式比较好理解。
	 * 不过效率最好的当然还是自底向上的递推了。
	 */
	private static int finish(int idx, int weight) {
		if (idx == 0 || weight - weights[idx - 1] < 0)
			return 0;
		
		int zero = finish(idx - 1, weight); // put into
		int one = finish(idx - 1, weight - weights[idx - 1]) + values[idx - 1]; // not put into

		return Math.max(zero, one);
	}

	public static void main(String[] args) {

		int maxWeight = 11;
		int[] values = new int[] { 20, 10, 12 };
		int[] weights = new int[] { 5, 4, 3 };

		System.out.println(pack(maxWeight, values, weights));
	}
}
