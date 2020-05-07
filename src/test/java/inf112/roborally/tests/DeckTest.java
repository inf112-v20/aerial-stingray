package inf112.roborally.tests;

import inf112.roborally.cards.Deck;
import inf112.roborally.cards.ProgramCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeckTest {

    private Deck deck;

    @Before
    public void initialize() {
        deck = new Deck();
    }

    @Test
    public void sizeTest() {
        assertEquals(deck.size(), Deck.DECK_SIZE);
    }

    @Test
    public void testRecycleSizeIsConsistent() {
        ProgramCard card = deck.pop();
        assertEquals(Deck.DECK_SIZE - 1, deck.size());

        deck.recycle(card);
        assertEquals(Deck.DECK_SIZE, deck.size());
    }

    @Test
    public void testRecycleAllSizes() {
        sizeTest();  // Before

        ProgramCard[] toBeRecycled = deck.take(5);
        assertEquals(5, toBeRecycled.length);

        assertEquals(Deck.DECK_SIZE - toBeRecycled.length, deck.size());  // During

        deck.recycleAll(toBeRecycled);
        sizeTest();  // After
    }

    @Test
    public void testNoNullCards() {
        for (ProgramCard c : deck)
            assertNotNull(c);
    }

    @Test
    public void testTakeCardsNotNull() {
        Deck deck = new Deck();
        for (ProgramCard card : deck.take(Deck.DECK_SIZE)) {
            assertNotNull(card);
        }
    }
}
