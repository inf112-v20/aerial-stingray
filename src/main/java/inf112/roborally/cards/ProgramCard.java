package inf112.roborally.cards;

public class ProgramCard {

    private CardTypes type;
    private int priority;

    public ProgramCard(CardTypes type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    public CardTypes getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
}
