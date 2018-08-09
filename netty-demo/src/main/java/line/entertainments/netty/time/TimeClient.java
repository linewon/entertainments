package line.entertainments.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * keys:
 * 1. Bootstrap & ServerBootstrap
 * 2. bootstrap.connect() & .bind()
 * @author line
 *
 */
@AllArgsConstructor
@Slf4j
public class TimeClient {
	
	private String host;
	private int port;

	public void run() throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workerGroup);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});
			
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
		
		log.info("client shutdown");
	}
	
	public static void main(String[] args) throws InterruptedException {
		String host = "localhost";
		int port = 19003;
		
		new TimeClient(host, port).run();
	}
}
