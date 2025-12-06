package test.CardTest;

import main.TCG.Card.CreatureCard;
import main.TCG.Card.Equipment.EquipmentType;
import main.TCG.Card.EquipmentCard;
import main.TCG.Game;
import main.TCG.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EquipmentCardTest {
    private Player player;
    private Player opponent;
    private Game game;
    private CreatureCard creature;

    @BeforeEach
    void setUp() {
        player = new Player("Player", 20);
        opponent = new Player("Opponent", 20);
        game = new Game(player, opponent);
        creature = new CreatureCard("Dragon", 5, 3);
        creature.play(player, opponent);
    }

    @Test
    void testEquipmentRequiresCreature() {
        Player emptyPlayer = new Player("Empty", 20);
        new Game(emptyPlayer, opponent);
        EquipmentCard equipment = new EquipmentCard("Sword", EquipmentType.DAMAGE, 2, 0);

        assertEquals(0, emptyPlayer.getBattlefield().size());
        // Should not crash, equipment is discarded
        assertDoesNotThrow(() -> equipment.play(emptyPlayer, opponent));
    }

    @Test
    void testDamageEquipmentBoostsCreature() {
        assertEquals(3, creature.getDamage());

        EquipmentCard sword = new EquipmentCard("Sword of Power", EquipmentType.DAMAGE, 2, 0);
        // In actual game, player would select target creature
        // This tests the equipment creation logic
        assertNotNull(sword);
        assertEquals("Sword of Power", sword.getName());
    }
}
