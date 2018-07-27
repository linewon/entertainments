package line.entertains.coding.leetcode;

/**
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 * 
 * https://blog.csdn.net/u011954296/article/details/51746150
 * 
 * @author line
 *
 */
public class Solution45 {

	/**
	 * int[] nums = new int[] { 7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3 }; 错了
	 */
	public int jump(int[] nums) {
		int count = 0;

		int l = 0, r = 0; // 左右边界
		while (r < nums.length - 1) {
			int next_r = 0;
			// 这里的 for 循环，是为了计算 下一次 while 循环时的左右边界！
			for (int i = l; i <= r; i++) { // 这里用到的贪心的思想，不容易想到
				next_r = Math.max(next_r, i + nums[i]);
			}
			l = r + 1;
			r = next_r;

			count++;
		}

		return count;
	}

	public static void main(String[] args) {

//		int[] nums = new int[] { 1 };
		 int[] nums = new int[] { 1, 2, 1, 4, 1, 2, 0, 1, 1, 9, 4 };
		// int[] nums = new int[] { 7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3 };
		System.out.println(new Solution45().jump(nums));
	}
}
