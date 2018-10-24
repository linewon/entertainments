package chinaums.test;

import java.nio.charset.Charset;
import java.util.Scanner;

public class Solution1_1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		while (scn.hasNextLine()) {
			String result = null;

			String str = scn.nextLine();
//			System.out.println(str);

			String[] lines = str.split(" ");

			StringBuilder sb = new StringBuilder();
			try {
				for (String line : lines) {
//					System.out.println(line);
					sb.append(func(line));
				}
				result = sb.toString();
			} catch (Exception e) {
				result = "输入格式错误";
			}
			System.out.println(result);
		}
		scn.close();
	}

	private static String func(String line) {

		String[] parts = line.split(":");
		if (!("FF21".equals(parts[0]) || "FF22".equals(parts[0])))
			throw new IllegalArgumentException(); // TAG不对，抛异常
		if (parts.length != 2) // 格式不对，抛异常
			throw new IllegalArgumentException();

		byte[] len = countLength(parts[1]);
		byte[] body = parts[1].getBytes(Charset.forName("utf8"));
		
		StringBuilder sb = new StringBuilder(parts[0]);
		sb.append(parseToHex(len));
		sb.append(parseToHex(body));
		
		return sb.toString();
	}
	
	private static byte[] countLength(String part2) {
		
		int len = part2.length();
		if (len > Byte.MAX_VALUE) // 输入超限，抛异常
			throw new IllegalArgumentException();
		
		byte[] result = new byte[1];
		result[0] = (byte) len;
		
		return result;
	}
	
	private static String parseToHex(byte[] ori) {
		
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < ori.length; k++) {
			if ((ori[k] & 0xFF) < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(ori[k] & 0xFF, 16));
		}
		return sb.toString();
	}
	
	
}
