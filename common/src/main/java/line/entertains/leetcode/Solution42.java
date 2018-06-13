package line.entertains.leetcode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/description/
 * 
 * 分层，并从第一层开始计算可以接收的水量，累和
 * 
 * @author line
 */
public class Solution42 {

	public int trap(int[] height) {

		int w = 0;
		int[] curHR = new int[2]; // int[] {head, rear}
		curHR[1] = height.length - 1;
		int layer = 1;

		/*
		 * 从第一层开始，往上去收集水
		 */
		while (true) {
			getCurLayerHeadAndRear(height, curHR, layer);
			int curLayer = water(height, curHR, layer);
			if (curLayer == -1)
				break;

			w += curLayer;
			layer++;
		}

		return w;
	}

	/**
	 * 找出当前层，第一个不为0、最后一个不为0的下标 如果全为0，或整层只有一个不为0，则返回 int[]{-1,-1}，表示再也积累不到雨水
	 */
	private void getCurLayerHeadAndRear(int[] height, int[] result, int layer) {
		// result[] 存放的是上一层的 头尾
		int head = result[0], rear = result[1];
		while (head < rear - 1) {
			if (height[head] >= layer)
				break;
			head++;
		}

		while (head < rear - 1) {
			if (height[rear] >= layer)
				break;
			rear--;
		}

		if (head < rear - 1) {
			result[0] = head;
			result[1] = rear;
		} else {
			result[0] = -1;
		}
	}

	private int water(int[] height, int[] result, int layer) {
		int cur = result[0], end = result[1];
		if (cur == -1)
			return -1;

		cur++;
		int w = 0;
		while (cur < end) {
			if (height[cur] < layer) {
				w++;
			}
			cur++;
		}
		return w;
	}

	public static void main(String[] args) {
		int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(new Solution42().trap(height));
	}
}