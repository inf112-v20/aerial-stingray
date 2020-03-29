package inf112.roborally.server;

import com.badlogic.gdx.Gdx;
import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final RoboRally game;

    public ClientHandler(RoboRally game) {
        this.game = game;
    }

    /*
    Format for sending messages:
    Word nr 1 = HEADER

    HEADER = START
    Word nr 2 = NAME of the player to add to list of players

    HEADER = CARD
    Word nr 2 = NAME of the player to add to list of players
    Word nr 3 = ROTATION of the card
    Word nr 4 = MOVE DISTANCE of the card
    Word nr 5 = PRIORITY of the card
     */

    @Override
    public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
        String data = message.toString();
        String[] split = data.split(" ");
        String header = split[0];

        switch (header) {
            case "HANDSHAKE":
                game.connected = true;
                System.out.println("CLIENT HANDSHAKE.");
                break;

            case "LIST":
                int size = Integer.parseInt(split[1]);
                while (game.playerNames.size() < size) {
                    game.playerNames.add("temp");
                }
                break;

            case "START":
                String name = split[1];
                int id = Integer.parseInt(split[2]);
                if (!game.playerNames.contains(name)) {
                    game.playerNames.set(id, name);
                }
                game.playersInGame = game.playerNames.size();

                break;
        }

    }
}
