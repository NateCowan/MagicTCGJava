package main.TCG.AI;

import main.TCG.Card.Card;
import main.TCG.Card.CreatureCard;
import main.TCG.Player;

import java.util.List;

// Template Pattern
public abstract class AbstractAIPlayer extends Player {

    public AbstractAIPlayer(String name, int health) {
        super(name, health);
    }

    public void playTurn(Player opponent) {
        int cardsPlayed = 0;
        int maxCardsPerTurn = 3;

        // Play up to 3 cards
        while (cardsPlayed < maxCardsPerTurn && !getHand().isEmpty()) {
            Card cardToPlay = chooseCardToPlay(opponent);
            if (cardToPlay != null) {
                System.out.println(getName() + " plays " + cardToPlay.getName());
                cardToPlay.play(this, opponent);
                getHand().remove(cardToPlay);
                cardsPlayed++;
            } else {
                break; // No more cards AI wants to play
            }
        }

        if (cardsPlayed == 0) {
            System.out.println(getName() + " plays no cards this turn.");
        }
    }

    // Subclasses implement this to define AI behavior
    protected abstract Card chooseCardToPlay(Player opponent);
}