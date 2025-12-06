package test.CardTest;

import main.TCG.Card.*;
import main.TCG.Player;
import main.TCG.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreatureCardTest {
    private CreatureCard creature;
    private Player player;
    private Player opponent;
    private Game game;

    @BeforeEach
    void setUp() {
        creature = new CreatureCard("Test Dragon", 5, 3);
        player = new Player("Player", 20);
        opponent = new Player("Opponent", 20);
        game = new Game(player, opponent);
    }

    @Test
    void testCreatureInitialization() {
        assertEquals("Test Dragon", creature.getName());
        assertEquals(5, creature.getHealth());
        assertEquals(3, creature.getDamage());
        assertEquals(5, creature.getCurrentHealth());
        assertTrue(creature.hasSummoningSickness());
        assertFalse(creature.isTapped());
    }

    @Test
    void testSummoningSickness() {
        assertTrue(creature.hasSummoningSickness());
        assertFalse(creature.canAttack());
        assertTrue(creature.canBlock());

        creature.startOfTurn();
        assertFalse(creature.hasSummoningSickness());
        assertTrue(creature.canAttack());
    }

    @Test
    void testTapping() {
        creature.setTapped(true);
        assertTrue(creature.isTapped());
        assertFalse(creature.canBlock());

        creature.setTapped(false);
        assertFalse(creature.isTapped());
        assertTrue(creature.canBlock());
    }

    @Test
    void testCombatDamage() {
        assertEquals(5, creature.getCurrentHealth());

        creature.takeCombatDamage(2);
        assertEquals(3, creature.getCurrentHealth());
        assertTrue(creature.isAlive());

        creature.takeCombatDamage(3);
        assertEquals(0, creature.getCurrentHealth());
        assertFalse(creature.isAlive());
    }

    @Test
    void testDamageReset() {
        creature.takeCombatDamage(3);
        assertEquals(2, creature.getCurrentHealth());

        creature.startOfTurn();
        assertEquals(5, creature.getCurrentHealth());
        assertTrue(creature.isAlive());
    }

    @Test
    void testPlayCreature() {
        assertEquals(0, player.getBattlefield().size());
        creature.play(player, opponent);
        assertEquals(1, player.getBattlefield().size());
        assertEquals(creature, player.getBattlefield().get(0));
    }
}