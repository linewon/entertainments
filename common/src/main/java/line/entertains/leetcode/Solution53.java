package line.entertains.leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 * https://www.cnblogs.com/coderJiebao/p/Algorithmofnotes27.html
 * http://www.cnblogs.com/en-heng/p/3970231.html
 * 
 * 
 * 加入让你记住头尾下标，又应该怎么做呢？
 * @author line
 *
 */
public class Solution53 {

	public int maxSubArray(int[] nums) {

		int endHere = nums[0]; // 局部最优
		int soFar = nums[0]; // 全局最优

		for (int i = 1; i < nums.length; i++) {
			endHere = Math.max(nums[i], nums[i] + endHere);
			soFar = Math.max(endHere, soFar);
		}
		return soFar;
	}

	/**
	 * dp[i] 存放以 nums[i] 为"结尾"的连续序列的最大和
	 * 
	 * 状态转移方程： dp[i] = max{nums[i], dp[i-1] + nums[i]}
	 */
	public int maxSubArray2(int[] nums) {

		int[] dp = new int[nums.length + 1];
		dp[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
		}

		int max = dp[0];
		for (int i = 1; i < dp.length; i++) {
			if (max < dp[i])
				max = dp[i];
		}

		return max;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(new Solution53().maxSubArray(nums));
		System.out.println(new Solution53().maxSubArray2(nums));
	}
}
