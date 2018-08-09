package line.entertainments.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeClientHandler extends ChannelInboundHandlerAdapter {


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		try {
			
//			String tmp = in.toString(0, 19, CharsetUtil.UTF_8);
			String tmp = in.toString(CharsetUtil.UTF_8);
			long curTime = in.readLong();
			log.info("client receive the time response: {}", tmp + curTime);
			
			ctx.close();
		} finally {
			in.release();
		}
    }
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("exception happened!", cause);
		ctx.close();
	}
}
