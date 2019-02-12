package line.entertains.io.file;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 
 * 
 * @author line
 * @date 2019年1月30日 下午4:21:50
 */
public class ApacheFileList {
	/**
	 * 将GBK编码格式的文件转换为UTF-8编码格式的文件
	 * 
	 * @author 高焕杰
	 */

	public static void main(String[] args) throws Exception {

		String gbkDirPath = "D:\\git\\smart-city\\smartcity-base\\smartcity-base-model\\src";//GBK编码格式源码文件路径
		String utf8DirPath = "D:\\git\\smart-city\\smartcity-base\\smartcity-base-model\\tmp";//转为UTF-8编码格式源码文件保存路径

		Collection<File> gbkFileList = FileUtils.listFiles(new File(gbkDirPath), new String[] { "java" }, true);//获取所有java文件
		for (File gbkFile : gbkFileList) {
			List<String> content = FileUtils.readLines(gbkFile, "GBK");
			String utf8FilePath = utf8DirPath + gbkFile.getAbsolutePath().substring(gbkDirPath.length());//UTF-8编码格式文件保存路径
			FileUtils.writeLines(new File(utf8FilePath), "UTF-8", content);//使用GBK编码格式读取文件，然后用UTF-8编码格式写入数据
		}
	}

}
