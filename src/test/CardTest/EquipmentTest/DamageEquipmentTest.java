package test.CardTest.EquipmentTest;

import main.TCG.Card.CreatureCard;
import main.TCG.Card.Equipment.DamageEquipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class DamageEquipmentTest {
    private CreatureCard baseCreature;
    private DamageEquipment equipped;

    @BeforeEach
    void setUp() {
        baseCreature = new CreatureCard("Dragon", 5, 3);
        equipped = new DamageEquipment(baseCreature, "Sword of Power", 2);
    }

    @Test
    void testDamageBonus() {
        assertEquals(3, baseCreature.getDamage());
        assertEquals(5, equipped.getDamage()); // 3 + 2
        assertEquals(5, equipped.getHealth()); // Unchanged
    }

    @Test
    void testNameDisplay() {
        assertTrue(equipped.getName().contains("Dragon"));
        assertTrue(equipped.getName().contains("Sword of Power"));
    }

    @Test
    void testForwardsMethods() {
        assertEquals(baseCreature.getHealth(), equipped.getHealth());
        assertTrue(equipped.isAlive());

        equipped.takeCombatDamage(3);
        assertEquals(2, equipped.getCurrentHealth());
    }
}
