package line.entertains.common.reflect.asm;

import java.util.Arrays;

import com.esotericsoftware.reflectasm.FieldAccess;

import line.entertains.common.entitys.Student;

public class Main {

	public static void main(String[] args) {
		
		Class<Student> clz = Student.class;
		FieldAccess fieldAccess = FieldAccess.get(clz);
		System.out.println(Arrays.toString(fieldAccess.getFieldNames()));
	}
}
