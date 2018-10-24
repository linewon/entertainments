package chinaums.test;

import java.util.Scanner;

public class Solution1_2 {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		
		double already = 0;
		while (scn.hasNextDouble()) {
			
			double money = scn.nextDouble();
			money += already;
			if (money - 100 > 0) {
				already = 0;
				System.out.println("超过限额");
			} else {
				already = money;
				System.out.println("消费成功");
			}
		}
		scn.close();
	}
}
