package line.entertains.io.file;

import java.io.File;

import org.junit.Test;

/**
 * 
 * 
 * @author line
 * @date 2019年1月10日 下午6:41:48
 */
public class FileDemo {

	
	/**
	 * in maven, relative path begins from src fold
	 */
	@Test
	public void relativePath() {
		String path = "src/main/resources/text/json.txt";
		File file = new File(path);
		System.out.println(file.exists());
	}
	
	@Test
	public void absolutePath() {
		String path = "d:/mygit/entertainments/common/src/main/resources/text/json.txt";
		File file = new File(path);
		System.out.println(file.exists());
	}
}
