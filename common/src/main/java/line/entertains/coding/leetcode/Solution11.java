package line.entertains.coding.leetcode;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/description/
 * 
 * container-with-most-water
 * 
 * @author line
 */
public class Solution11 {
	
	public static void main(String[] args) {
		
		System.out.println(new Solution11().maxArea(new int[] {1,2,3,4,5}));
	}

	public int maxArea(int[] height) {
		
		int head = 0, rear = height.length - 1;
		int maxSize = -1;
		
		while (head < rear) {
			
//			System.out.println("head - rear :\t\t" + head + " - " + rear);
			int curSize = size(head, height[head], rear, height[rear]);
//			System.out.println("curSize :\t\t" + curSize);
			
			if (curSize > maxSize)
				maxSize = curSize;
				
			/*
			 * 这里可以优化，可以跳过一部分
			 */
			if (height[head] <= height[rear]) { // 相等的情况再考虑考虑
				head++;
			} else {
				rear--;
			}
		}
		
		return maxSize;
	}
	
	private int size(int head, int height1, int rear, int height2) {
		int height = height1 < height2 ? height1 : height2;
		int width = rear - head;
		
		return height * width;
	}
}
