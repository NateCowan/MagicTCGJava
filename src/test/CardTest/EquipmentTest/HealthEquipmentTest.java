package test.CardTest.EquipmentTest;

import main.TCG.Card.CreatureCard;
import main.TCG.Card.Equipment.HealthEquipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class HealthEquipmentTest {
    private CreatureCard baseCreature;
    private HealthEquipment equipped;

    @BeforeEach
    void setUp() {
        baseCreature = new CreatureCard("Knight", 3, 2);
        equipped = new HealthEquipment(baseCreature, "Shield of Defense", 3);
    }

    @Test
    void testHealthBonus() {
        assertEquals(3, baseCreature.getHealth());
        assertEquals(6, equipped.getHealth()); // 3 + 3
        assertEquals(2, equipped.getDamage()); // Unchanged
    }
}
