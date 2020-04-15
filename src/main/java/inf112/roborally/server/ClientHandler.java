package inf112.roborally.server;

import com.badlogic.gdx.Gdx;
import inf112.roborally.cards.ProgramCard;
import inf112.roborally.screens.RoboRally;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final RoboRally game;

    public ClientHandler(RoboRally game) {
        this.game = game;
    }

    /*
    Format for sending messages (one word at the time split with a space):
    1. word = HEADER

    if HEADER = START
    2. word = NAME of the player to add to list of players

    if HEADER = CARD
    2. word = NAME of the player to add to list of players
    3. word = CARD TYPE of the card
    4. word = PRIORITY of the card
     */

    @Override
    public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
        String data = message.toString();
        String[] split = data.split(" ");
        String header = split[0];

        switch (header) {
            /*
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

            case "START": {
                String name = split[1];
                int id = Integer.parseInt(split[2]);
                if (!game.playerNames.contains(name)) {
                    game.playerNames.set(id, name);
                }
                game.playersInGame = game.playerNames.size();

                break;
            }
            // TODO: add cases for map, cards and several events
            // Finish SET_MAP
            case "SET_MAP":
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        System.out.printf("Amount of players = %d%n", game.amountOfPlayers);
                        //
                    }
                });
                break;

            case "CARD": {
                String[] cardSplit = split[1].split(" ");
                ProgramCard card;
                String name = "";
                ArrayList<ProgramCard> everyCard = new ArrayList<>();
                for (int i = 0; i < split.length - 2; i++) {
                    name = split[i];
                    String cardType = split[i + 1];
                    String priority = split[i + 2].split(" ")[0];
                    card = new ProgramCard(cardType, priority);
                    everyCard.add(card);
                }
                //multiplayer logic here
                break;
            }

            case "ALL_READY":
                //multiplayer logic;
                break;

            case "MULTI":
                //game.multiplayer = true;
                break;

            case "RECEIVE_CARD": {
                final String name = split[1];
                String cardType = split[2];
                String priority = split[3];
                final ProgramCard card = new ProgramCard(cardType, priority);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        //multiplayer logic
                    }
                });
                break;
            }

            case "POWER_DOWN":
                String sender = split[1];
                //board logic
                break;

            case "POWER_UP":
                break;

            case "SET_NUMBER_OF_PLAYERS":
                Integer amountOfPlayers = Integer.parseInt(split[1]);
                System.out.println("Amount of players: " + amountOfPlayers);
                //game.setAmountOfPlayers(amountOfPlayers);
                break;

            case "DIED":
                System.out.println(split[1] + " is dead.");
                game.playersInGame--;
                //removePlayer(split[1]);
                break;

            case "REMOVED":
                System.out.println(split[1] + " left the game.");
                //removePlayer(split[1]);
                break;

            default:
                System.out.println(data);
                break;
        */
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        System.out.println("Host disconnected");
        context.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.flush();
    }

}
