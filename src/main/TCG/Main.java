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
        Player player = new Player("Player", 20);
        AbstractAIPlayer aiPlayer = new RandomAIPlayer("AI Player", 20);
        AbstractAIPlayer aggressiveAIPlayer = new AggressiveAIPlayer("Aggresive AI Player", 20);

        List<Card> deck1 = deckBuilder.buildDeck(100);
        List<Card> deck2 = deckBuilder.buildDeck(100);
        List<Card> deck3 = deckBuilder.buildDeck(100);

        player.setDeck(deck1);
        aggressiveAIPlayer.setDeck(deck2);
        aiPlayer.setDeck(deck3);

        Game game = new Game(player, aiPlayer);
        game.start();
    }
}