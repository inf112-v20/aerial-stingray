package inf112.roborally.util;

/**
 * A pair of Player & ProgramCard.
 */
public class Pair<A, B> {

    private final A first;
    private final B snd;


    public Pair(A a, B b) {
        this.first = a;
        this.snd = b;
    }

    public A getFirst() {
        return first;
    }

    public B getSnd() {
        return snd;
    }
}
