package line.entertainments.netty.codec.handler.inbound;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * send a current time string when make connection
 * @author line
 *
 */
@Slf4j
public class TimeHandler extends ChannelInboundHandlerAdapter {
	
	/**
	 * when make connection:
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf time = ctx.alloc().buffer(4); // 声明一个4个字节的 ByteBuf
		// 这里的字符串长度肯定大于4个字节
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		log.info("send message. curTime: {}, len: {}", dateNow, dateNow.getBytes().length);
		time.writeBytes(dateNow.getBytes());
		ctx.writeAndFlush(time);
	}
	
//	@Override
//	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//		ByteBuf time = ctx.alloc().buffer(4); // 声明一个4个字节的 ByteBuf
//		// 这里的字符串长度肯定大于4个字节
//		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		log.info("send message. curTime: {}, len: {}", dateNow, dateNow.getBytes().length);
//		time.writeBytes(dateNow.getBytes());
//		ctx.writeAndFlush(time);
//	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("EXCEPTION CAUGHT!", cause);
		ctx.close();
	}
}
