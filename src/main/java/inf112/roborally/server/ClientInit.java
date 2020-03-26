package inf112.roborally.server;

import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ClientInit extends ChannelInitializer<SocketChannel> {
    private final RoboRally game;

    public ClientInit(RoboRally game) {
        this.game = game;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // Empty because not implemented yet, but wanted to remove errors in Client.java
    }
}
