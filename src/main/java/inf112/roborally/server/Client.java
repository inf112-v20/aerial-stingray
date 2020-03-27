package inf112.roborally.server;

import inf112.roborally.cards.Deck;
import inf112.roborally.entities.Player;
import inf112.roborally.screens.RoboRally;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.List;
import java.util.Stack;

public class Client implements Runnable {
    private final String host;
    private final int port;
    private RoboRally game;
    private Stack<Deck> cards;
    private String name;
    private List<Player> playersConnected;
    Channel channel;

    public Client(String host, int port, RoboRally game, String name) {
        this.host = host;
        this.port = port;
        this.game = game;
        this.name = name;
    }

    @Override
    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // Bootstrap is similar to ServerBootstrap except that it's not for non-server channels such as a
            // client-side or connectionless channel.
            Bootstrap bootstrap = new Bootstrap()
                    // If you specify only one EventLoopGroup, it will be used both as a boss group and as a worker group.
                    // The boss worker is not used for the client side though.
                    .group(workerGroup)
                    // Instead of NioServerSocketChannel, NioSocketChannel is being used to create a client-side Channel
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInit(game));
            channel = null;
            try {
                // You can also use bind() instead of connect
                channel = bootstrap.connect(host, port).sync().channel();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            channel.writeAndFlush("HANDSHAKE" + " " + name);

            //prevents the client from shutting down. Gets shut down manually
            while (true) {

            }

        } finally {
            // Shut down application without making any troubles
            workerGroup.shutdownGracefully();
        }
    }

    public void sendMessage(String msg) {
        channel.writeAndFlush(msg + "\r\n");
    }

    public Channel getChannel() {
        return channel;
    }

    public List<Player> getPlayersConnected() {
        return playersConnected;
    }
}
