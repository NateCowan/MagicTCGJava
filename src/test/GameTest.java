package test;

import main.TCG.*;
import main.TCG.Card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Player player1;
    private Player player2;
    private Game game;

    @BeforeEach
    void setUp() {
        player1 = new Player("Player1", 20);
        player2 = new Player("Player2", 20);
        game = new Game(player1, player2);

        List<Card> deck1 = DeckBuilder.buildDeck(50);
        List<Card> deck2 = DeckBuilder.buildDeck(50);
        player1.setDeck(deck1);
        player2.setDeck(deck2);
    }

    @Test
    void testGameInitialization() {
        assertNotNull(game);
        assertNotNull(game.getEventManager());
    }

    @Test
    void testGameIsOverWhenPlayerDead() {
        assertFalse(game.isOver());

        player1.takeDamage(20);
        assertTrue(game.isOver());
    }

    @Test
    void testGameIsOverWhenOutOfCards() {
        assertFalse(game.isOver());

        player1.getDeck().clear();
        player1.getHand().clear();
        assertTrue(game.isOver());
    }

    @Test
    void testCombatDamageToPlayer() {
        CreatureCard attacker = new CreatureCard("Dragon", 5, 4);
        attacker.play(player1, player2);
        attacker.startOfTurn(); // Remove summoning sickness

        List<CreatureCard> attackers = List.of(attacker);
        Map<CreatureCard, CreatureCard> blockers = new java.util.HashMap<>();

        assertEquals(20, player2.getHealth());
        game.resolveCombat(player1, player2, attackers, blockers);
        assertEquals(16, player2.getHealth()); // Took 4 damage
    }

    @Test
    void testCombatBetweenCreatures() {
        CreatureCard attacker = new CreatureCard("Dragon", 5, 4);
        CreatureCard blocker = new CreatureCard("Knight", 3, 3);

        attacker.play(player1, player2);
        blocker.play(player2, player1);

        attacker.startOfTurn();
        blocker.startOfTurn();

        List<CreatureCard> attackers = List.of(attacker);
        Map<CreatureCard, CreatureCard> blockers = new java.util.HashMap<>();
        blockers.put(attacker, blocker);

        game.resolveCombat(player1, player2, attackers, blockers);

        // Dragon takes 3 damage (5-3=2 HP left)
        // Knight takes 4 damage (3-4=-1 HP, dies)
        assertTrue(attacker.isAlive());
        assertFalse(blocker.isAlive());
    }
}