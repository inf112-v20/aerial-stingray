package inf112.roborally.server;


import inf112.roborally.cards.Deck;
import inf112.roborally.cards.ProgramCard;
import inf112.roborally.screens.RoboRally;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Stack;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private final RoboRally game;
    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public HashMap<Channel, String> connectedPlayers;
    public Deck deckOfCards;
    public Stack<ProgramCard> returnedCards;

    public ServerHandler(RoboRally game) {
        this.game = game;
        connectedPlayers = new HashMap<>();
        deckOfCards = new Deck();
        returnedCards = new Stack<>();
    }

    public void checkIfAllConnectedPlayersAreReady() {
        System.out.printf("%d of %d connected players are ready", game.readyPlayers, game.playersInGame);
        if(game.readyPlayers == game.playersInGame) {
            System.out.println("All connected players are now ready to rumble!");
            for (Channel channel :
                    channels) {
                channel.writeAndFlush("PLAYERS_READY PAYLOAD\r\n");
            }
            game.readyPlayers = 0;
        }
    }

    public void onePlayerIsReady() {
        game.readyPlayers++;
        System.out.println("A player is ready!");
        System.out.printf("%d of %d connected players are ready", game.readyPlayers, game.playersInGame);
        if (game.readyPlayers == game.playersInGame) {
            System.out.println("Every connected player is ready!");
            for (Channel channel :
                    channels) {
                channel.writeAndFlush("PLAYERS_READY PAYLOAD\r\n");
            }
            game.readyPlayers = 0;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel :
                channels) {
            if (channel != incoming) channel.writeAndFlush("[ " + incoming.remoteAddress() + " ] " + message + "\n");
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
        String data = message.toString();
        String[] split = data.split(" ", 2);
        String header = split[0];

        switch (header) {
            case "HANDSHAKE":
                System.out.println("[SERVER] " + split[1] + " connected!");
                game.playerNames.add(split[1]);
                connectedPlayers.put((context.channel()), split[1]);
                context.channel().writeAndFlush("SERVER HANDSHAKE");

                for (Channel channel :
                        channels) {
                    channel.writeAndFlush(split[1] + " connected!" + "\r\n");
                }
                break;

            case "REQUEST_CARDS":
                String[] cardLimitAndName = split[1].split(" ");
                String name = cardLimitAndName[1].trim();
                int cardLimit = Integer.parseInt(cardLimitAndName[0]);
                for (int i = 0; i < cardLimit; i++) {
                    String card = deckOfCards.pop().toString();
                    for (Channel channel :
                            channels) {
                        channel.writeAndFlush("RECEIVE_CARD " + name + " " + card + "\r\n");
                    }
                }
                break;

            case "CARD":
                String saveSplit1 = split[1];
                String[] cardSplit = split[1].split(" ");

                // Come back to this piece of code of something goes wrong
                for (String s : cardSplit) {
                    String[] cardInformation = s.split(" ");
                    String cardType = cardInformation[1];
                    String priority = cardInformation[2];
                    ProgramCard card = new ProgramCard(cardType, priority);
                    returnedCards.add(card);
                }

                for (Channel channel :
                        channels) {
                    channel.writeAndFlush(split[0] + " " + saveSplit1 + "\r\n");
                }
                onePlayerIsReady();
                break;

            case "POWER_DOWN":
                for (Channel channel :
                        channels) {
                    channel.writeAndFlush(split[0] + " " + split[1] + "\r\n");
                }
                onePlayerIsReady();
                break;

            case "UPDATEREADY":
                onePlayerIsReady();
                break;

            default:
                for (Channel channel :
                        channels) {
                    channel.writeAndFlush(split[0] + " " + split[1] + "\r\n");
                }
                break;
        }

    }

    // These two context handler functions may seem like unused code, but they are being called by netty it self,
    // and not this class

    public void ctxHandlerAdded(ChannelHandlerContext context) {
        channels.add(context.channel());
    }

    public void ctxHandlerRemoved(ChannelHandlerContext context) {
        System.out.println("Removed context handler");
        Channel incomingChannel = context.channel();
        for (Channel channel :
                channels) {
            if (channel != incomingChannel) {
                channel.writeAndFlush("REMOVED" + " " + connectedPlayers.get(incomingChannel) + "\r\n");
            }
        }
        game.playersInGame--;
        checkIfAllConnectedPlayersAreReady();
        channels.remove(context.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
    }

}
