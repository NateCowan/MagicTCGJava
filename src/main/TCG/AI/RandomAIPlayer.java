package main.TCG.AI;

import main.TCG.Card.Card;
import main.TCG.Player;

import java.util.*;

public class RandomAIPlayer extends AbstractAIPlayer {

    private Random random = new Random();

    public RandomAIPlayer(String name, int health) {
        super(name, health);
    }

    @Override
    protected Card chooseCardToPlay(Player opponent) {
        List<Card> hand = getHand();
        if (hand.isEmpty()) return null;
        return hand.get(random.nextInt(hand.size()));
    }
}