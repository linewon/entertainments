package line.entertainments.netty.codec.handler.inbound;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigStringHandler extends ChannelInboundHandlerAdapter {

	/**
	 * when make connection:
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		String path = "d:/test.txt";
		
		FileInputStream fis = new FileInputStream(new File(path));
		FileChannel fc = fis.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(4 * 1024);
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		while (fc.read(buffer) != -1) {
			i++;
			buffer.flip();
			ByteBuf buf = Unpooled.wrappedBuffer(buffer);
			
			String text = buf.toString(CharsetUtil.US_ASCII);
			sb.append(text);
			log.info("read text[{}] from file:\n{}\nlength: {}", i, text, text.length());
			
//			ctx.write(buf);
			buffer.clear();
		}
		
		ByteBuf resultBuf = ctx.alloc().buffer();
		resultBuf.writeBytes(sb.toString().getBytes());
		
		/*
		 * 据说这样优化，可以避免线程切换，相对于：
		 * ctx.writeAndFlush()来说的话。
		 */
		ctx.channel().eventLoop().execute(new Runnable() {
			@Override
			public void run() {
				ctx.writeAndFlush(resultBuf);
			}
		});
		fis.close();
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("EXCEPTION CAUGHT!", cause);
		ctx.close();
	}
	
	private String bufferToString(ByteBuffer buffer) throws CharacterCodingException {
		
		Charset charset = CharsetUtil.US_ASCII;
		CharsetDecoder decoder = charset.newDecoder();
		
		// 直接decode ByteBuffer 会把里面的内容取出来
		CharBuffer charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
		return charBuffer.toString();
	}
}
