package line.entertainments.netty.echo;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EchoServer {

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
							ch.pipeline().addLast(new EchoServerHandler());
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
		
		new EchoServer(port).run();
	}
}
