package chinaums.test;

import java.util.Scanner;

public class Solution1_3 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		while (scn.hasNextLine()) {
			try {

				String line = scn.nextLine();
				System.out.println(func(line));
			} catch (Exception e) {
				System.out.println("NONE");
			}
		}

		scn.close();
	}

	private static String func(String line) {
		System.out.println(line);

		/*
		 * 0: tag 1: len 2: body
		 */
		int flg = 0;
		int idx = 0;
		int bodyLen = 0;
		StringBuilder sb = new StringBuilder();
		while (idx < line.length()) {
			switch (flg) {
			case 0:
				String tag = line.substring(idx, idx + 4);
				if (!("FF21".equals(tag) || "FF22".equals(tag)))
					throw new IllegalArgumentException();
				sb.append(tag);
				idx += 4;
				flg = 1;
				break;
			case 1:
				String len = line.substring(idx, idx + 2);
				bodyLen = 2 * parseToByte(len)[0];
				sb.append(':');
				idx += 2;
				flg = 2;
				break;
			case 2:
				String bodyHex = line.substring(idx, idx + bodyLen);
				String body = new String(parseToByte(bodyHex));
				sb.append(body).append('\n');
				flg = 0;
				idx += bodyLen;
				break;
			default:
				break;
			}
		}

		return sb.toString();
	}

	private static byte[] parseToByte(String hexstring) {
		if ((hexstring == null) || (hexstring.length() % 2 != 0)) {
			return new byte[0];
		}

		byte[] dest = new byte[hexstring.length() / 2];

		for (int i = 0; i < dest.length; i++) {
			String val = hexstring.substring(2 * i, 2 * i + 2);
			dest[i] = (byte) Integer.parseInt(val, 16);
		}
		return dest;
	}
}
