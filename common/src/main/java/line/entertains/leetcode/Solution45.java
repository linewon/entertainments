package line.entertains.leetcode;

/**
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 * 
 * https://www.cnblogs.com/boring09/p/4231771.html
 * 
 * @author line
 *
 */
public class Solution45 {

	public int jump(int[] nums) {
		int reach = 0;
		int count = 0;
		for (int i = 0; i <= reach; i = reach) {
			reach = Math.max(reach, nums[i] + i);
			count++;
			if (reach >= nums.length - 1)
				break;
		}
		return count;
	}

	public static void main(String[] args) {

		int[] nums = new int[] { 1, 2, 1, 3, 1, 2, 0, 1, 9, 4 };
		System.out.println(new Solution45().jump(nums));
	}
}
