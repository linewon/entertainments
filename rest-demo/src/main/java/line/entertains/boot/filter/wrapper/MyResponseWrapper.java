package line.entertains.boot.filter.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyResponseWrapper extends HttpServletResponseWrapper {

	ByteArrayOutputStream output;
	FilterServletOutputStream filterOutput;

	public MyResponseWrapper(HttpServletResponse response) {
		super(response);
		output = new ByteArrayOutputStream();
	}

	/**
	 * 重写父类的getOutputStream方法，让reponse都写到filterOutput，既output之中。
	 */
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (filterOutput == null) {
			filterOutput = new FilterServletOutputStream(output);
		}
		return filterOutput;
	}

	/**
	 * 由于想把outputStream中的数据读取出来，只能够借助bytearrayOutputStream
	 */
	public byte[] getBytes() {
		return output.toByteArray();
	}
}
