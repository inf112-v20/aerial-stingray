package inf112.roborally.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck extends Stack<ProgramCard> {

    public final static int DECK_SIZE = 84;

    /**
     * Creating a this with: (standard)
     * - 18 move1
     * - 12 move2
     * - 6 move3
     * - 6 backup
     * - 18 rotate_right
     * - 18 rotate_left
     * - 6 u-turn
     */
    public Deck() {
        super();
        ArrayList<Integer> randomPriorities = getRandomPriorities();

        for (int i = 0; i < 18; i++) {
            ProgramCard card = new ProgramCard(CardType.MOVE1, randomPriorities.remove(0));
            this.add(card);
        }
        for (int i = 0; i < 12; i++) {
            ProgramCard card = new ProgramCard(CardType.MOVE2, randomPriorities.remove(0));
            this.add(card);
        }
        for (int i = 0; i < 6; i++) {
            ProgramCard card = new ProgramCard(CardType.MOVE3, randomPriorities.remove(0));
            this.add(card);
        }
        for (int i = 0; i < 6; i++) {
            ProgramCard card = new ProgramCard(CardType.BACKUP, randomPriorities.remove(0));
            this.add(card);
        }
        for (int i = 0; i < 18; i++) {
            ProgramCard card = new ProgramCard(CardType.TURN_RIGHT, randomPriorities.remove(0));
            this.add(card);
        }
        for (int i = 0; i < 18; i++) {
            ProgramCard card = new ProgramCard(CardType.TURN_LEFT, randomPriorities.remove(0));
            this.add(card);
        }
        for (int i = 0; i < 6; i++) {
            ProgramCard card = new ProgramCard(CardType.TURN_U, randomPriorities.remove(0));
            this.add(card);
        }

        shuffle();
    }

    /**
     * Gets a list, which is equal in size as size of deck, with integers 10 to
     * deck.size()*10 in a randomized order.
     *
     * @return A list with unique integers in a randomized order
     */
    private ArrayList<Integer> getRandomPriorities() {
        ArrayList<Integer> randomPriorities = new ArrayList<>();

        for (int i = 1; i < DECK_SIZE + 1; i++) {
            randomPriorities.add(i * 10);
        }

        Collections.shuffle(randomPriorities);
        return randomPriorities;
    }

    /**
     * Shuffles the deck. (pseudo-random)
     */
    public void shuffle() {
        Collections.shuffle(this);
    }

    /**
     * Method for drawing specified amount of cards.
     *
     * @param amount Number of cards to draw
     * @return An ArrayList<ProgramCard> with size 'amount'
     */
    public ArrayList<ProgramCard> take(int amount) {
        ArrayList<ProgramCard> cardsToDraw = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cardsToDraw.add(this.pop());
        }

        return cardsToDraw;
    }

    /**
     * Recycles the card given - puts it into the deck and shuffles the deck.
     *
     * @param card The card to recycle
     */
    public void recycle(ProgramCard card) {
        this.add(card);
    }

    /**
     * Method for recycling a list of cards.
     *
     * @param cards The cards to recycle
     */
    public void recycleAll(List<ProgramCard> cards) {
        for (ProgramCard card : cards) {
            this.recycle(card);
        }
        shuffle();
    }
}
