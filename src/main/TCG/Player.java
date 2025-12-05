package main.TCG;

import main.TCG.Card.Card;
import main.TCG.Card.ICreature;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private List<Card> hand = new ArrayList<>();
    private List<Card> deck = new ArrayList<>();
    private List<ICreature> battlefield = new ArrayList<>();
    private Game game;

    public Player(String name, int health) { this.name = name; this.health = health; }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public List<Card> getHand() { return hand; }
    public List<Card> getDeck() { return deck; }
    public void setDeck(List<Card> newDeck) { this.deck = newDeck; }
    public List<ICreature> getBattlefield() { return battlefield; }

    public void takeDamage(int amount) { health -= amount; }
    public void heal(int amount) { health += amount; }

    public Card drawCard() {
        Card card = null;
        if (!deck.isEmpty()) {
            card = deck.get(0);
            hand.add(deck.remove(0));
        }
        return card;
    }

    public void addCardToDeck(Card card) { deck.add(card); }
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
}