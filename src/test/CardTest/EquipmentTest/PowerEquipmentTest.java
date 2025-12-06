package test.CardTest.EquipmentTest;

import main.TCG.Card.CreatureCard;
import main.TCG.Card.Equipment.PowerEquipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class PowerEquipmentTest {
    private CreatureCard baseCreature;
    private PowerEquipment equipped;

    @BeforeEach
    void setUp() {
        baseCreature = new CreatureCard("Warrior", 4, 3);
        equipped = new PowerEquipment(baseCreature, "Ring of Power", 2, 2);
    }

    @Test
    void testBothStatsBoosted() {
        assertEquals(3, baseCreature.getDamage());
        assertEquals(4, baseCreature.getHealth());

        assertEquals(5, equipped.getDamage()); // 3 + 2
        assertEquals(6, equipped.getHealth()); // 4 + 2
    }
}
