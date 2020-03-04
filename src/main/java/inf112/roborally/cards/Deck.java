package inf112.roborally.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {

    public final static int DECK_SIZE = 84;
    private Stack<ProgramCard> deck;

    /**
     * Creating a deck with: (standard)
     * - 18 move1
     * - 12 move2
     * - 6 move3
     * - 6 backup
     * - 18 rotate_right
     * - 18 rotate_left
     * - 6 u-turn
     */
    public Deck() {
        deck = new Stack<>();
        ArrayList<Integer> randomPriorities = getRandomPriorities();

        for (int i = 0; i < 18; i++) {
            ProgramCard card = new ProgramCard(CardTypes.MOVE1, randomPriorities.remove(0));
            deck.add(card);
        }
        for (int i = 0; i < 12; i++) {
            ProgramCard card = new ProgramCard(CardTypes.MOVE2, randomPriorities.remove(0));
            deck.add(card);
        }
        for (int i = 0; i < 6; i++) {
            ProgramCard card = new ProgramCard(CardTypes.MOVE3, randomPriorities.remove(0));
            deck.add(card);
        }
        for (int i = 0; i < 6; i++) {
            ProgramCard card = new ProgramCard(CardTypes.BACKUP, randomPriorities.remove(0));
            deck.add(card);
        }
        for (int i = 0; i < 18; i++) {
            ProgramCard card = new ProgramCard(CardTypes.TURN_RIGHT, randomPriorities.remove(0));
            deck.add(card);
        }
        for (int i = 0; i < 18; i++) {
            ProgramCard card = new ProgramCard(CardTypes.TURN_LEFT, randomPriorities.remove(0));
            deck.add(card);
        }
        for (int i = 0; i < 6; i++) {
            ProgramCard card = new ProgramCard(CardTypes.TURN_U, randomPriorities.remove(0));
            deck.add(card);
        }
    }

    private ArrayList<Integer> getRandomPriorities() {
        ArrayList<Integer> randomPriorities = new ArrayList<>();

        // 84 different priorities, one for each card
        for (int i = 1; i < DECK_SIZE + 1; i++) {
            randomPriorities.add(i * 10);
        }

        Collections.shuffle(randomPriorities);
        return randomPriorities;
    }

    public Stack<ProgramCard> getCards() {
        return deck;
    }
}
