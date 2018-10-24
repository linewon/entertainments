package line.entertains.test;

import java.util.Base64;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import io.netty.util.CharsetUtil;

/**
 * hex --> byte[] --> String
 */
public class Base64Test {
	
	public static String byBase64(String base64) {
		byte[] result = Base64.getDecoder().decode(base64);
		return new String(result, CharsetUtil.UTF_8);
	}
	
	public static String func(String hex) {
		
		
		
		byte[] result = new HexBinaryAdapter().unmarshal(hex);
		return new String(result, CharsetUtil.UTF_8);
	}
	
	public static String hexString2ByteArr(String hexstring) {
		if ((hexstring == null) || (hexstring.length() % 2 != 0)) {
			return null;
		}

		byte[] dest = new byte[hexstring.length() / 2];

		for (int i = 0; i < dest.length; i++) {
			String val = hexstring.substring(2 * i, 2 * i + 2);
			dest[i] = (byte) Integer.parseInt(val, 16);
		}
		return new String(dest, CharsetUtil.UTF_8);
	}
	
	public static void main(String[] args) {
		
		String string = "0000010cc8bbaecf94025c38d5cc78f5195f066bb22b80d50f84336cedc3543d923e944dd591ecb788ab8e645facc645cd32e0d73fb937dfda9c8d1ce2f9aa92906c8e5a341270e7841a775d4c0b0efd2534397e55f4f484f0c5577c1b2b4d788ec185c2631b4b3d335a37bd730d02e5338ce7dffe05df0901a1dda1fbbf18cd8f4d3621befee0715d20ac7c07e26d7cb76c5fb8a40241644f26e6c7cb4e7ac460f19096eb9005c86f2c212bb3325b692997267e9fe43464f0b5fb0fcdbd48c566a1655a66646b385a86a062317b801d6ef2c21a";
		System.out.println(func(string));
	}
}
