package line.entertains.io.bio.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * File copy in BIO.
 * https://blog.csdn.net/u010520692/article/details/51491171
 * 
 * @author line
 *
 */
public class FileCopy {

	/**
	 * file --> inputStream --> byte[] --> outputStream --> file
	 * 
	 * @throws IOException
	 */
	public static void copyFileByBufferedIS(String srcPath, String dstPath) throws IOException {

		// buffer
		byte[] buffer = new byte[4 * 1024];
		// 获取输入
		FileInputStream fis = new FileInputStream(new File(srcPath));
		BufferedInputStream bis = new BufferedInputStream(fis);
		// 输出
		FileOutputStream fos = new FileOutputStream(new File(dstPath));
		BufferedOutputStream bos = new BufferedOutputStream(fos);

//		System.out.println("\nSTART ...");
		// copy
		int length = -1;
		while ((length = bis.read(buffer)) != -1) {
			bos.write(buffer, 0, length);
		}

//		System.out.println("DONE!");

		bis.close();
		fis.close();
		bos.close();
		fos.close();
	}

	public static void main(String[] args) {

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
				copyFileByBufferedIS(srcPath, dstPath + i + ".iso");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		
		double takes = (endTime - startTime) / 1000.0;
		System.out.println("bis costs [" + takes + "] seconds!");
	}
}
