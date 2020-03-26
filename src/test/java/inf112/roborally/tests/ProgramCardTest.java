package inf112.roborally.tests;

import inf112.roborally.cards.CardType;
import inf112.roborally.cards.ProgramCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramCardTest {

    private ProgramCard card;

    @Test
    public void testCorrectType() {
        card = new ProgramCard(CardType.MOVE1, 0);
        assertEquals(card.getType(), CardType.MOVE1);

        card = new ProgramCard(CardType.MOVE2, 0);
        assertEquals(card.getType(), CardType.MOVE2);

        card = new ProgramCard(CardType.MOVE3, 0);
        assertEquals(card.getType(), CardType.MOVE3);

        card = new ProgramCard(CardType.BACKUP, 0);
        assertEquals(card.getType(), CardType.BACKUP);

        card = new ProgramCard(CardType.TURN_LEFT, 0);
        assertEquals(card.getType(), CardType.TURN_LEFT);

        card = new ProgramCard(CardType.TURN_RIGHT, 0);
        assertEquals(card.getType(), CardType.TURN_RIGHT);

        card = new ProgramCard(CardType.TURN_U, 0);
        assertEquals(card.getType(), CardType.TURN_U);

    }

    @Test
    public void testCorrectPriority() {
        card = new ProgramCard(CardType.MOVE1, 5);
        assertEquals(card.getPriority(), 5);

        card = new ProgramCard(CardType.MOVE1, 10);
        assertEquals(card.getPriority(), 10);
    }
}
