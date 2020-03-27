package inf112.roborally.server;

import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@SuppressWarnings("Duplicates")
public class ClientInit extends ChannelInitializer<SocketChannel> {
    private final RoboRally game;

    public ClientInit(RoboRally game) {
        this.game = game;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 2.1 param is the maximum length of the decoded frame is 8192
        // 2.2 param is the delimiter, which is one or more characters that separate text strings
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new ClientHandler(game));
    }
}
