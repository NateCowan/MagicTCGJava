package test.CardTest.EquipmentTest;

import main.TCG.Card.CreatureCard;
import main.TCG.Card.Equipment.DamageEquipment;
import main.TCG.Card.Equipment.EquipmentDecorator;
import main.TCG.Card.Equipment.HealthEquipment;
import main.TCG.Card.ICreature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class EquipmentStackingTest {
    private CreatureCard baseCreature;

    @BeforeEach
    void setUp() {
        baseCreature = new CreatureCard("Dragon", 5, 3);
    }

    @Test
    void testMultipleEquipmentStack() {
        ICreature equipped1 = new DamageEquipment(baseCreature, "Sword", 2);
        ICreature equipped2 = new DamageEquipment(equipped1, "Axe", 1);
        ICreature equipped3 = new HealthEquipment(equipped2, "Armor", 3);

        assertEquals(6, equipped3.getDamage()); // 3 + 2 + 1
        assertEquals(8, equipped3.getHealth()); // 5 + 3
    }

    @Test
    void testGetBaseCreature() {
        ICreature equipped1 = new DamageEquipment(baseCreature, "Sword", 2);
        ICreature equipped2 = new HealthEquipment(equipped1, "Armor", 3);

        if (equipped2 instanceof EquipmentDecorator) {
            ICreature base = ((EquipmentDecorator) equipped2).getBaseCreature();
            assertEquals(baseCreature, base);
        }
    }
}
