package inf112.roborally.tests;

import inf112.roborally.cards.Deck;
import inf112.roborally.cards.ProgramCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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

        ArrayList<ProgramCard> toBeRecycled = deck.take(5);
        assertEquals(5, toBeRecycled.size());

        assertEquals(Deck.DECK_SIZE - toBeRecycled.size(), deck.size());  // During

        deck.recycleAll(toBeRecycled);
        sizeTest();  // After
    }
}
