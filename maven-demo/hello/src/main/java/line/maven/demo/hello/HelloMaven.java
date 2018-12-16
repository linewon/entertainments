package line.maven.demo.hello;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSONObject;

public class HelloMaven {

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("say", "hello maven!");
		
		System.out.println(jsonObject.toJSONString());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println(df.format(new Date()));
//				System.gc(); // 好像真的有点用额
			}
		}, 0, 1000);
		System.out.println("LOG HERE");
	}
}
