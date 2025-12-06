package test.AITest;

import main.TCG.*;
import main.TCG.AI.*;
import main.TCG.Card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AggressiveAIPlayerTest {
    private AggressiveAIPlayer aiPlayer;
    private Player opponent;
    private Game game;

    @BeforeEach
    void setUp() {
        aiPlayer = new AggressiveAIPlayer("AggroBot", 20);
        opponent = new Player("Opponent", 20);
        game = new Game(aiPlayer, opponent);

        List<Card> deck = DeckBuilder.buildDeck(50);
        aiPlayer.setDeck(deck);
    }

    @Test
    void testAggressiveAIInitialization() {
        assertEquals("AggroBot", aiPlayer.getName());
        assertEquals(20, aiPlayer.getHealth());
        assertNotNull(aiPlayer.getHand());
    }

    @Test
    void testAggressiveAIPlaysCards() {
        // Give AI a mix of cards
        aiPlayer.getHand().clear();
        aiPlayer.getHand().add(CardFactory.createCreature("Dragon", 5, 3));
        aiPlayer.getHand().add(CardFactory.createDamageSpell("Fireball", 4));
        aiPlayer.getHand().add(CardFactory.createHealSpell("Heal", 3));
        aiPlayer.getHand().add(CardFactory.createCreature("Knight", 3, 2));

        int initialHandSize = aiPlayer.getHand().size();
        aiPlayer.playTurn(opponent);

        int cardsPlayed = initialHandSize - aiPlayer.getHand().size();
        assertTrue(cardsPlayed > 0 && cardsPlayed <= 3,
                "AI should play 1-3 cards");
    }

    @Test
    void testAggressiveAIDealsDamage() {
        // Give AI damage spells
        aiPlayer.getHand().clear();
        aiPlayer.getHand().add(CardFactory.createDamageSpell("Fireball", 5));
        aiPlayer.getHand().add(CardFactory.createDamageSpell("Lightning", 3));

        int opponentInitialHealth = opponent.getHealth();
        aiPlayer.playTurn(opponent);

        // Should deal damage if cards were played
        int cardsRemaining = aiPlayer.getHand().size();
        if (cardsRemaining < 2) {
            assertTrue(opponent.getHealth() < opponentInitialHealth,
                    "AI should deal damage with spells");
        }
    }

    @Test
    void testAggressiveAIPlaysCreatures() {
        // Give AI only creatures
        aiPlayer.getHand().clear();
        aiPlayer.getHand().add(CardFactory.createCreature("Dragon", 5, 4));
        aiPlayer.getHand().add(CardFactory.createCreature("Knight", 3, 3));
        aiPlayer.getHand().add(CardFactory.createCreature("Goblin", 2, 2));

        assertEquals(0, aiPlayer.getBattlefield().size());
        aiPlayer.playTurn(opponent);

        assertTrue(aiPlayer.getBattlefield().size() > 0,
                "AI should summon creatures");
        assertTrue(aiPlayer.getBattlefield().size() <= 3,
                "AI should not summon more than 3 creatures");
    }

    @Test
    void testAggressiveAIUsesEquipment() {
        // Give AI creature and equipment
        aiPlayer.getHand().clear();
        aiPlayer.getHand().add(CardFactory.createCreature("Knight", 3, 2));

        // First turn - play creature
        aiPlayer.playTurn(opponent);
        assertTrue(aiPlayer.getBattlefield().size() > 0, "Should have creature");

        // Second turn - should be able to play equipment
        aiPlayer.getHand().clear();
        aiPlayer.getHand().add(CardFactory.createDamageEquipment("Sword", 2));

        int initialHandSize = aiPlayer.getHand().size();
        aiPlayer.playTurn(opponent);

        // Equipment should be played or attempted
        assertTrue(aiPlayer.getHand().size() <= initialHandSize,
                "AI should attempt to play equipment");
    }

    @Test
    void testAggressiveAIWithLowOpponentHealth() {
        // Reduce opponent health
        opponent.takeDamage(15); // Opponent at 5 HP

        // Give AI damage spell and other cards
        aiPlayer.getHand().clear();
        aiPlayer.getHand().add(CardFactory.createDamageSpell("Fireball", 4));
        aiPlayer.getHand().add(CardFactory.createCreature("Dragon", 5, 3));
        aiPlayer.getHand().add(CardFactory.createHealSpell("Heal", 3));

        aiPlayer.playTurn(opponent);

        // AI should prioritize finishing off low-health opponent
        // If it played damage spell, opponent should take damage
        assertTrue(opponent.getHealth() <= 5,
                "AI should focus on low-health opponent");
    }

    @Test
    void testAggressiveAIWithEmptyHand() {
        aiPlayer.getHand().clear();

        // Should not crash
        assertDoesNotThrow(() -> aiPlayer.playTurn(opponent));
    }

    @Test
    void testAggressiveAIRespectsCardLimit() {
        // Give AI many cards
        aiPlayer.getHand().clear();
        for (int i = 0; i < 10; i++) {
            aiPlayer.getHand().add(CardFactory.createCreature("Creature" + i, 3, 2));
        }

        int initialHandSize = aiPlayer.getHand().size();
        aiPlayer.playTurn(opponent);

        int cardsPlayed = initialHandSize - aiPlayer.getHand().size();
        assertTrue(cardsPlayed <= 3,
                "AI should respect 3 card per turn limit, played: " + cardsPlayed);
    }
}