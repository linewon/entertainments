package line.entertains.common.charset;

import java.nio.charset.Charset;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BytesCompare {

	public static void main(String[] args) {
		String text = "123abc中文中文";
//		String text = "123abc";

		byte[] utf8 = text.getBytes(CharsetUtil.UTF_8);
		byte[] ascii = text.getBytes(CharsetUtil.US_ASCII);
		byte[] gbk = text.getBytes(Charset.forName("GBK"));

		
		log.info("ascii == utf8 : {}", equal(ascii, utf8));
		log.info("utf8 == gbk : {}", equal(utf8, gbk));
		

		byte[] utf82 = CharsetUtil.UTF_8.encode(text).array();
		log.info("utf8 == utf82 : {}", equal(utf8, utf82));
	}

	private static boolean equal(byte[] a, byte[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i])
				return false;
		}
		return true;
	}
}
