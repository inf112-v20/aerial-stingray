package inf112.roborally.tests;

import inf112.roborally.cards.CardType;
import inf112.roborally.cards.ProgramCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramCardTest {

    @Test
    public void testCorrectType() {
        ProgramCard card = new ProgramCard(CardType.MOVE1, 0);

        assertEquals(card.getType(), CardType.MOVE1);
    }

    @Test
    public void testCorrectPriority() {
        ProgramCard card = new ProgramCard(CardType.MOVE1, 5);

        assertEquals(card.getPriority(), 5);
    }
}
