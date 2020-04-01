package inf112.roborally.server;


import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private final RoboRally game;

    public ServerHandler(RoboRally game) {
        this.game = game;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        // Code will be implemented.
    }
}
