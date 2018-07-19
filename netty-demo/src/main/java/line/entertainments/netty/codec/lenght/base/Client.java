package line.entertainments.netty.codec.lenght.base;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import line.entertainments.netty.codec.handler.inbound.TimeHandler;
import lombok.AllArgsConstructor;

/**
 * test of :
 * LengthFieldBasedFrameDecoder(decoder. byte[]->String) & LengthFieldPrepender(encode. String->byte[])
 * 
 * @author line
 *
 */
@AllArgsConstructor
public class Client {

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
					/*
					 * 这个地方的顺序，一定要搞清楚额！！！！！
					 * inbound & outbound
					 * inbound only
					 * outboud only
					 */
					ch.pipeline().addLast(new LengthFieldPrepender(4, true));
					/*
					 * 这里，客户端的配置，和服务器的要匹配起来
					 */
//					ch.pipeline().addLast(new LengthFieldPrepender(4));
					ch.pipeline().addLast(new TimeHandler());
				}
			});

			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		String host = "localhost";
		int port = 19004;

		new Client(host, port).run();
	}
}
