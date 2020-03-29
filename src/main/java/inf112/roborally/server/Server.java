package inf112.roborally.server;

import inf112.roborally.screens.RoboRally;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;

public class Server {

    private final int port;
    private final RoboRally game;
    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    ServerBootstrap bootstrap;

    public Server(int port, RoboRally game) {
        this.port = port;
        this.game = game;
    }

}
