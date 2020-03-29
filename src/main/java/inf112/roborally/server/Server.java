package inf112.roborally.server;

import inf112.roborally.screens.RoboRally;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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

    //@Override
    public void run() {
        System.out.println("Setting up game server...");
        // This is a server-side application and therefore two NioEventLoopGroup will be used
        // accepts an incoming connection
        bossGroup = new NioEventLoopGroup();
        // handles the traffic of the accepted connection once the boss accepts the connection and registers the
        // accepted connection to the worker.
        workerGroup = new NioEventLoopGroup();

        try {
            //helper class that sets up a server
            bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    // specify to use the NioServerSocketChannel class which is used to instantiate a new Channel to
                    // accept incoming connections.
                    .channel(NioServerSocketChannel.class)
                    // the handler specified here will always be evaluated by a newly accepted Channel.
                    .childHandler(new ServerInit(game)).childOption(ChannelOption.AUTO_READ, true);

            try {
                // Bind and start to accept incoming connections.
                bootstrap.bind(port).sync().channel().closeFuture().sync();
            // A mandatory catch function to catch any exceptions caused by .sync()
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        } finally {
            // Shut down server without causing errors.
            System.out.println("Shutting down game server...");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

}
