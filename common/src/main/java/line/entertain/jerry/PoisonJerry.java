package line.entertain.jerry;

import java.util.Arrays;

/**
 * @author line
 */
public class PoisonJerry {

	/**
	 * 根据老鼠死亡结果推断毒药编号
	 * 老鼠：rat, mouse
	 * 复数：rat -> rats, mouse -> mice
	 */
	private static int jerry(final int[] rats) {
		int numOfPoison = 0;
		for (int i = 0; i < rats.length; i++) {
			if (rats[i] != 0)
				numOfPoison += (1 << (rats.length - 1 - i));
		}
		return numOfPoison;
	}
	
	/**
	 * 根据老鼠个数和毒药编号获得老鼠死亡结果
	 * @param numOfRat 老鼠个数
	 * @param numOfPoison 毒药编号
	 */
	private static int[] jerry(final int numOfRat, final int numOfPoison) {

		/**
		 * 老鼠喝药情况的数组
		 * index : 老鼠的编号s0, s1, s2
		 * value : (value & numOfPoison) != 0 表示老鼠喝过这瓶毒药，对应index的老鼠给药死了
		 */
		int[] jerryDrinkPoison = new int[numOfRat];
		for (int i = 0; i < numOfRat; i++) { // 初始化喝药情况
			jerryDrinkPoison[i] = 1 << (numOfRat - 1 - i);
		}
		for (int i = 0; i < numOfRat; i++) { // 为了看的清楚一点写了两个循环
			jerryDrinkPoison[i] = (numOfPoison & jerryDrinkPoison[i]) == 0 ? 0 : 1;
		}
		return jerryDrinkPoison;
	}
	
	public static void main(String[] args) {

		/**
		 * 老鼠的死亡情况
		 */
		int[] deathOfRat = new int[] { 0, 0, 0, 1, 1, 0, 1, 0, 1, 1 };
//		int[] deathOfRat = new int[] { 0, 1, 1 };
		/**
		 * 毒药的编号
		 */
		int numOfPoison = jerry(deathOfRat);
		deathOfRat = jerry(deathOfRat.length, numOfPoison);
		
		System.out.println(numOfPoison);
		System.out.println(Arrays.toString(deathOfRat));
	}
}