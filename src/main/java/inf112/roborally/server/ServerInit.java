package inf112.roborally.server;

import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@SuppressWarnings("Duplicates")
// ChannelInitializer<C> -> special ChannelInboundHandler which offers easy way to initialize a Channel once it was registered to its EventLoop
public class ServerInit extends ChannelInitializer<SocketChannel> {
    private RoboRally game;

    public ServerInit(RoboRally game) {
        this.game = game;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new ServerHandler(game));
    }
}
