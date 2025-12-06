package test;

import main.TCG.*;
import main.TCG.Card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Player opponent;
    private Game game;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer", 20);
        opponent = new Player("Opponent", 20);
        game = new Game(player, opponent);

        List<Card> deck = DeckBuilder.buildDeck(50);
        player.setDeck(deck);
    }

    @Test
    void testPlayerInitialization() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(20, player.getHealth());
        assertNotNull(player.getHand());
        assertNotNull(player.getBattlefield());
        assertNotNull(player.getDeck());
    }

    @Test
    void testTakeDamage() {
        assertEquals(20, player.getHealth());
        player.takeDamage(5);
        assertEquals(15, player.getHealth());

        player.takeDamage(20);
        assertEquals(-5, player.getHealth());
    }

    @Test
    void testHeal() {
        player.takeDamage(10);
        assertEquals(10, player.getHealth());

        player.heal(5);
        assertEquals(15, player.getHealth());
    }

    @Test
    void testDrawCard() {
        int initialDeckSize = player.getDeck().size();
        int initialHandSize = player.getHand().size();

        Card drawn = player.drawCard();

        assertNotNull(drawn);
        assertEquals(initialDeckSize - 1, player.getDeck().size());
        assertEquals(initialHandSize + 1, player.getHand().size());
    }

    @Test
    void testDrawFromEmptyDeck() {
        player.getDeck().clear();
        Card drawn = player.drawCard();
        assertNull(drawn);
    }

    @Test
    void testBattlefield() {
        assertEquals(0, player.getBattlefield().size());

        CreatureCard creature = new CreatureCard("Dragon", 5, 3);
        creature.play(player, opponent);

        assertEquals(1, player.getBattlefield().size());
        assertEquals(creature, player.getBattlefield().get(0));
    }
}
