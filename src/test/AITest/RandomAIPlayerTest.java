package test.AITest;

import main.TCG.*;
import main.TCG.AI.*;
import main.TCG.Card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RandomAIPlayerTest {
    private RandomAIPlayer aiPlayer;
    private Player opponent;
    private Game game;

    @BeforeEach
    void setUp() {
        aiPlayer = new RandomAIPlayer("RandomBot", 20);
        opponent = new Player("Opponent", 20);
        game = new Game(aiPlayer, opponent);

        List<Card> deck = DeckBuilder.buildDeck(50);
        aiPlayer.setDeck(deck);

        // Draw some cards
        for (int i = 0; i < 10; i++) {
            aiPlayer.drawCard();
        }
    }

    @Test
    void testRandomAIInitialization() {
        assertEquals("RandomBot", aiPlayer.getName());
        assertEquals(20, aiPlayer.getHealth());
        assertNotNull(aiPlayer.getHand());
        assertNotNull(aiPlayer.getBattlefield());
    }

    @Test
    void testRandomAIPlaysCards() {
        int initialHandSize = aiPlayer.getHand().size();
        assertTrue(initialHandSize > 0, "AI should have cards in hand");

        // AI plays turn
        aiPlayer.playTurn(opponent);

        // AI should have played up to 3 cards
        int cardsPlayed = initialHandSize - aiPlayer.getHand().size();
        assertTrue(cardsPlayed >= 0 && cardsPlayed <= 3,
                "AI should play 0-3 cards, played: " + cardsPlayed);
    }

    @Test
    void testRandomAIPlaysMaxThreeCards() {
        // Give AI many cards
        for (int i = 0; i < 10; i++) {
            aiPlayer.drawCard();
        }

        int initialHandSize = aiPlayer.getHand().size();
        aiPlayer.playTurn(opponent);

        int cardsPlayed = initialHandSize - aiPlayer.getHand().size();
        assertTrue(cardsPlayed <= 3, "AI should not play more than 3 cards");
    }

    @Test
    void testRandomAIWithEmptyHand() {
        aiPlayer.getHand().clear();

        // Should not crash
        assertDoesNotThrow(() -> aiPlayer.playTurn(opponent));
        assertEquals(0, aiPlayer.getHand().size());
    }

    @Test
    void testRandomAICanPlayCreatures() {
        // Give AI only creatures
        aiPlayer.getHand().clear();
        for (int i = 0; i < 5; i++) {
            aiPlayer.getHand().add(CardFactory.createCreature("Dragon", 5, 3));
        }

        int initialBattlefield = aiPlayer.getBattlefield().size();
        aiPlayer.playTurn(opponent);

        // Should have played some creatures
        assertTrue(aiPlayer.getBattlefield().size() > initialBattlefield,
                "AI should play creatures to battlefield");
    }

    @Test
    void testRandomAICanPlayEffects() {
        // Give AI only damage spells
        aiPlayer.getHand().clear();
        for (int i = 0; i < 3; i++) {
            aiPlayer.getHand().add(CardFactory.createDamageSpell("Fireball", 3));
        }

        int opponentInitialHealth = opponent.getHealth();
        aiPlayer.playTurn(opponent);

        // Should have dealt damage
        assertTrue(opponent.getHealth() < opponentInitialHealth ||
                        aiPlayer.getHand().size() < 3,
                "AI should play effect cards");
    }
}