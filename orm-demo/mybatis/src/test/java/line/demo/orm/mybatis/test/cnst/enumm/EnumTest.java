package line.demo.orm.mybatis.test.cnst.enumm;

import org.junit.Test;

import line.demo.orm.mybatis.cnst.HumanGender;

public class EnumTest {

	@Test
	public void testEnum() {
		System.out.println(HumanGender.Male.toString());
		System.out.println(HumanGender.Male.getSex());
		

		System.out.println(HumanGender.Female.toString());
		System.out.println(HumanGender.Female.getSex());
	}
	
	@Test
	public void maleToEnum() {
		String male = "Male";
		HumanGender type = HumanGender.valueOf(male);
		System.out.println(type);
		
		String another = "Female";
		type = HumanGender.valueOf(another);
		System.out.println(type);
	}
}
