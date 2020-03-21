package inf112.roborally.tests;

import inf112.roborally.cards.Deck;
import org.junit.Before;
import org.junit.Test;

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
}
