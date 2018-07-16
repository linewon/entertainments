package line.entertainments.netty.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * keys:
 * 1. ChannelActive & ChannelInactive
 * 2. ChannelFutureListener
 * 3. ChannelHandlerContext in ChannelFutureListener
 * 
 * @author line
 *
 */
@Slf4j
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * WTF.
	 * channelActive: 连接建立时触发
	 * channelInactive: 连接断开时触发
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		ByteBuf time = ctx.alloc().buffer(4); // 声明一个4个字节的 ByteBuf
		// 这里的字符串长度肯定大于4个字节
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		log.info("curTime: {}, length: {}", dateNow, dateNow.getBytes().length);
		time.writeBytes(dateNow.getBytes());
		
		long curTime = System.currentTimeMillis();
		time.writeLong(curTime);
		log.info("write curTime: {}", curTime);
		
		ChannelFuture f = ctx.writeAndFlush(time);
		// 监听写操作完成之后，执行 operationComplete 方法
		f.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				log.info("operation complete. the channel is ready to close");
				
				/*
				 * 这里的close()，是两边都会close还是只有服务端会？
				 * 事实证明好像是两边你都会，还是搞清楚一下具体流程！
				 */
				ctx.close(); // 当写时间完成之后，关闭信道。
			}
		});
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("exception happened!", cause);
		ctx.close();
	}
}
