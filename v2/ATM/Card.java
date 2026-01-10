package ATM;

public class Card {

    private final long id;
    private final String name;

    public Card(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
