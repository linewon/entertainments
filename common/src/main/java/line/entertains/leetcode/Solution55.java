package line.entertains.leetcode;

/**
 * https://leetcode-cn.com/problems/jump-game/description/
 * 
 * https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51636861
 * 
 * @author line
 *
 */
public class Solution55 {

	public boolean canJump(int[] nums) {
		int reach = nums[0];
		/*
		 * i=1: 下标0已经赋给reach，所以从下标1开始 i < nums.length: 最后一个肯定不用验 i <= reach: 0的情况肯定是不允许的
		 */
		for (int i = 1; i < nums.length - 1 && i <= reach; i++) {
			reach = Math.max(reach, nums[i] + i);
		}
		return reach >= nums.length - 1;
	}

	public boolean canJump2(int[] nums) {
		int reach = 0;
		for (int i = 0; i <= reach; i++) {
			reach = Math.max(reach, nums[i] + i);
			if (reach >= nums.length - 1)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {

//		int[] nums = new int[] { 0 };
		 int[] nums = new int[] {1,2,1,2,1,0,1,9,4};
		System.out.println(new Solution55().canJump2(nums));
	}
}
