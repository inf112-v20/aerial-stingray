package inf112.roborally.server;

import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

@SuppressWarnings("Duplicates")
// ChannelInitializer<C> -> special ChannelInboundHandler which offers easy way to initialize a Channel once it was registered to its EventLoop
public class ServerInit extends ChannelInitializer<SocketChannel> {
    private RoboRally game;

    public ServerInit(RoboRally game) {
        this.game = game;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // Will be implemented for next commit
    }
}
