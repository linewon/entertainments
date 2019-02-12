package chinaums.test;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 先进入第一个评测页面，注意不要让光标进入任何文本框。 开始运行程序，回到浏览器，等待10秒，评测开始，等待几分钟就好了。 不要操作电脑。
 * 如果已经有部分人员已经评测，请注意修改循环次数120到实际人数，否则要手工停掉程序。
 * 
 * @author 焕文
 * 
 */
public class 民主测评 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Robot robot = new Robot();
		robot.delay(5000);
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < 130; i++) {
			type(robot, KeyEvent.VK_TAB);
			type(robot, KeyEvent.VK_TAB);
			type(robot, KeyEvent.VK_TAB);
			type(robot, KeyEvent.VK_TAB);
//			type(robot, KeyEvent.VK_TAB);
//			type(robot, KeyEvent.VK_TAB);
			type19(robot);
			type(robot, KeyEvent.VK_TAB);
			for (int j = 0; j < 4; j++) {
				int r = rand.nextInt(2);
				if (r == 0)
					type19(robot);
				else
					type20(robot);
			}
			type(robot, KeyEvent.VK_ENTER);
			robot.delay(500);
			type(robot, KeyEvent.VK_ENTER);
			robot.delay(1000);
		}
	}

	private static void type20(Robot robot) {
		type(robot, KeyEvent.VK_2);
		type(robot, KeyEvent.VK_0);
		type(robot, KeyEvent.VK_TAB);
	}

	private static void type19(Robot robot) {
		type(robot, KeyEvent.VK_1);
		type(robot, KeyEvent.VK_9);
		type(robot, KeyEvent.VK_TAB);
	}

	private static void type(Robot robot, int e) {
		robot.keyPress(e);
		robot.keyRelease(e);
	}

}
