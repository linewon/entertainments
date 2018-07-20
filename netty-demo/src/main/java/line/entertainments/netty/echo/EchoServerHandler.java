package line.entertainments.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * keys:
 * 1. ChannelInboundHandlerAdapter: channelActive(), channelRead(), channelInactive()
 * 2. ChannelHandlerContext: write(), alloc().buffer(), close()
 * 3. ByteBuf: toString(), read(), write()
 * 4. CompositeByteBuf: Unpooled.buffer()
 * 
 * @author line
 */
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		String message = in.toString(CharsetUtil.US_ASCII);
		log.info("receive and echo message: {}", message);
		
		long time = System.currentTimeMillis();
		String timeStr = String.valueOf(time);
		log.info("current time: {}", timeStr);
		
//		ByteBuf buf = Unpooled.buffer(4); // ctx.alloc().buffer(4);
//		buf.writeBytes((" --curTime:" + timeStr).getBytes());
//		
//		// CompositeByteBuf
//		ByteBuf resp = Unpooled.wrappedBuffer(in, buf);
		
		ChannelFuture f = ctx.write(in);
		f.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				ctx.close();
			}
		});
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}
	
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace(); 
		ctx.close();
	}
}
