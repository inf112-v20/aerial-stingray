package inf112.roborally.server;

import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final RoboRally game;

    public ClientHandler(RoboRally game) {
        this.game = game;
    }
}
