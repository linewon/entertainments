package line.entertainments.netty.codec.handler.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * receive the message, then log and discard it.
 * finally close the connection.
 * 
 * @author line
 */
@Slf4j
public class DiscardHandler extends ChannelInboundHandlerAdapter {
	

	/**
	 *  这里接收到的数据，是已经经过LengthFiledBasedFrameDecoder处理过报文长度后的字符串。
	 *  
	 *  receive and discard
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		String msgStr = buf.toString(CharsetUtil.UTF_8);
		log.info("receive message: {}", msgStr);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		ctx.close(); // they shoud be same...
		ctx.channel().close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("EXCEPTION CAUGHT!", cause);
		ctx.close();
	}
}
