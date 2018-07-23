package line.entertains.io.file.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * File copy in NIO
 * https://blog.csdn.net/suifeng3051/article/details/48160753
 * 
 * 测试用的是机械硬盘，磁盘IO有限，所以和BufferedInputStream比起来没什么差别。
 * @author line
 */
public class FileCopy {

	/**
	 * file --> inputStream --> channel --> buffer --> channel --> outputStream -->
	 * file
	 * 
	 * @throws IOException
	 */
	public static void copyFileByNIO(String srcPath, String dstPath) throws IOException {

		// 声明buffer
		ByteBuffer buffer = ByteBuffer.allocate(4 * 1024);

		// 获取输入
		FileInputStream fis = new FileInputStream(new File(srcPath));
		FileChannel fic = fis.getChannel();

		// 输出
		FileOutputStream fos = new FileOutputStream(new File(dstPath));
		FileChannel foc = fos.getChannel();

		System.out.println("\nSTART ...");
		// copy
		while (true) {

			int eof = fic.read(buffer);
			if (eof == -1)
				break;

			buffer.flip(); // ***
			foc.write(buffer);
			buffer.clear(); // ***
		}

		System.out.println("DONE!");

		fic.close();
		foc.close();
		fis.close();
		fos.close();
	}
	
	public static void writeIntoFile(String text, String path) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
		
		// 输出
		FileOutputStream fos = new FileOutputStream(new File(path));
		FileChannel foc = fos.getChannel();


		buffer.flip(); // ***
		foc.write(buffer);
		buffer.clear(); // ***
		
		foc.close();
		fos.close();
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		System.out.print("enter the source file path: ");
		String srcPath = sc.nextLine();
		System.out.println("srcPath : " + srcPath);

		System.out.print("enter the destination file path: ");
		String dstPath = sc.nextLine();
		System.out.println("dstPath : " + dstPath);
		sc.close();

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			try {
				copyFileByNIO(srcPath, dstPath + i + ".iso");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		
		double takes = (endTime - startTime) / 1000.0;
		System.out.println("bis costs [" + takes + "] seconds!");
	}
}
