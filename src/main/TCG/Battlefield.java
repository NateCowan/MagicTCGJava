package main.TCG;

import java.util.ArrayList;
import java.util.List;
import main.TCG.Card.Card;

public class Battlefield {
    private List<Card> playedCards = new ArrayList<>();

    public void add(Card card) {
        playedCards.add(card);
    }

    public List<Card> getCards() {
        return playedCards;
    }
}