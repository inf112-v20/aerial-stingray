package inf112.roborally.tests;

import inf112.roborally.cards.CardTypes;
import inf112.roborally.cards.ProgramCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramCardTest {

    @Test
    public void testCorrectType() {
        ProgramCard card = new ProgramCard(CardTypes.MOVE1, 0);

        assertEquals(card.getType(), CardTypes.MOVE1);
    }

    @Test
    public void testCorrectPriority() {
        ProgramCard card = new ProgramCard(CardTypes.MOVE1, 5);

        assertEquals(card.getPriority(), 5);
    }
}
