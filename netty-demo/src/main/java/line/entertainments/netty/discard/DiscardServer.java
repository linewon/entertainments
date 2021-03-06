package line.entertainments.netty.discard;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import lombok.AllArgsConstructor;

/**
 * keys:
 * 1. EventLoop & EventLoopGroup
 * 2. ServerBootstrap
 * 3. java.nio.SocketChannel & io.netty.channel.socket.SocketChannel
 * 4. NioServerSocketChannel & NioSocketChannel
 * 5. ChannelInitializer
 * 6. ChannelHandler & ChannelHandlerAdapter & ChannelInboundHandlerAdapter
 * 7. ChannelInboundHandlerAdapter & ChannelOutboundHandlerAdapter
 * 8. ChannelFuture
 * 9. ChannelPipeline
 * @author line
 *
 */
@AllArgsConstructor
public class DiscardServer {

	private int port;

	public void run() throws InterruptedException {
		EventLoopGroup boss = new NioEventLoopGroup(), worker = new NioEventLoopGroup();

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new DiscardServerHandler());
						}
					})
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
		} finally {
			worker.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 19003;
		}
		
		new DiscardServer(port).run();
	}
}
