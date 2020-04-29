package inf112.roborally.util;

import inf112.roborally.cards.ProgramCard;
import inf112.roborally.entities.Player;

/**
 * A pair of Player & ProgramCard.
 */
public class Pair {

    private final Player player;
    private final ProgramCard programCard;


    public Pair(Player player, ProgramCard programCard) {
        this.player = player;
        this.programCard = programCard;
    }

    public Player getPlayer() {
        return player;
    }

    public ProgramCard getProgramCard() {
        return programCard;
    }
}
