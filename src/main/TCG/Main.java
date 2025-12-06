package main.TCG;

import main.TCG.AI.AbstractAIPlayer;
import main.TCG.AI.AggressiveAIPlayer;
import main.TCG.AI.RandomAIPlayer;
import main.TCG.Card.Card;
import main.TCG.Card.CardFactory;
import main.TCG.Card.DeckBuilder;

import java.util.List;

public class Main {
    private static DeckBuilder deckBuilder = new DeckBuilder();
    public static void main(String[] args) {
        int PLAYER_HEALTH = 20;
        int DECK_SIZE = 100;

        Player player = new Player("Player", PLAYER_HEALTH);
        AbstractAIPlayer aiPlayer = new RandomAIPlayer("AI Player", PLAYER_HEALTH);
        AbstractAIPlayer aggressiveAIPlayer = new AggressiveAIPlayer("Aggresive AI Player", PLAYER_HEALTH);

        List<Card> deck1 = deckBuilder.buildDeck(DECK_SIZE);
        List<Card> deck2 = deckBuilder.buildDeck(DECK_SIZE);
        List<Card> deck3 = deckBuilder.buildDeck(DECK_SIZE);

        player.setDeck(deck1);
        aggressiveAIPlayer.setDeck(deck2);
        aiPlayer.setDeck(deck3);

        Game game = new Game(aggressiveAIPlayer, aiPlayer);
        game.start();
    }
}