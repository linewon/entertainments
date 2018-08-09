package line.entertainments.netty.codec.lenght.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import line.entertainments.netty.codec.handler.inbound.DiscardHandler;
import lombok.AllArgsConstructor;

/**
 * test of :
 * LengthFieldBasedFrameDecoder(decoder. byte[]->String) & LengthFieldPrepender(encode. String->byte[])
 * 
 * @author line
 *
 */
@AllArgsConstructor
public class Server {

	private int port;

	public void run() throws InterruptedException {

		EventLoopGroup boss = new NioEventLoopGroup(1), worker = new NioEventLoopGroup();

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							/*
							 * 这个地方的顺序，一定要搞清楚额！！！！！ inbound & outbound inbound only outboud only
							 */
							// 这里的maxLength和lengthFieldLength是存在一定关系的，不然又可能会报异常，或导致读到的数据有缺失
							ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 4, 0, 4));
							/*
							 * 这里，服务器的设置和客户端的要匹配起来
							 */
//							ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 4, -4, 4));
							ch.pipeline().addLast(new DiscardHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
		} finally {
			worker.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int port = 19004;

		new Server(port).run();
	}
}
