package line.entertains.common.callback;

import line.entertains.common.callback.async.ManagerB;
import line.entertains.common.callback.common.Manager;
import line.entertains.common.callback.sync.ManagerA;

/**
 * 老板、经理、员工（前端和后台）
 * 老板(Main)通知经理(Manager)需要开发什么任务, 
 * 经理把任务分给开发员工(Worker),
 * 员工完成任务之后调用经理的回调方法，
 * 经理在回调方法中向老板汇报工作
 * 
 * @author line
 *
 */
public class CallbackDemo {
	
	public static void syncCallback() {
		// 老板接到三个单子，通知经理A开发
		String job1 = "job1111111";
		
		Manager manager = new ManagerA();
		// 经理把工作分配给前端和后台
		manager.distributeJob(job1);
	}
	
	
	public static void asyncCallback() throws InterruptedException {
		// 老板接到三个单子，通知经理B开发
		String job1 = "job2222";
		
		Manager manager = new ManagerB();
		// 经理把工作分配给前端和后台
		manager.distributeJob(job1);
	}
	
	public static void main(String[] args) throws InterruptedException {
//		syncCallback();
		asyncCallback();
	}
}
