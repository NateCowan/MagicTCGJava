package test.CardTest;

import main.TCG.Card.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    @Test
    void testCreateCreature() {
        Card card = CardFactory.createCreature("Dragon", 5, 3);
        assertNotNull(card);
        assertTrue(card instanceof CreatureCard);
        assertEquals("Dragon", card.getName());
        assertEquals(5, ((CreatureCard) card).getHealth());
        assertEquals(3, ((CreatureCard) card).getDamage());
    }

    @Test
    void testCreateDamageSpell() {
        Card card = CardFactory.createDamageSpell("Fireball", 4);
        assertNotNull(card);
        assertTrue(card instanceof EffectCard);
        assertEquals("Fireball", card.getName());
    }

    @Test
    void testCreateHealSpell() {
        Card card = CardFactory.createHealSpell("Healing Touch", 5);
        assertNotNull(card);
        assertTrue(card instanceof EffectCard);
        assertEquals("Healing Touch", card.getName());
    }

    @Test
    void testCreateDrawSpell() {
        Card card = CardFactory.createDrawSpell("Insight", 2);
        assertNotNull(card);
        assertTrue(card instanceof EffectCard);
        assertEquals("Insight", card.getName());
    }

    @Test
    void testCreateDamageEquipment() {
        Card card = CardFactory.createDamageEquipment("Sword", 2);
        assertNotNull(card);
        assertTrue(card instanceof EquipmentCard);
        assertEquals("Sword", card.getName());
    }

    @Test
    void testCreateHealthEquipment() {
        Card card = CardFactory.createHealthEquipment("Shield", 3);
        assertNotNull(card);
        assertTrue(card instanceof EquipmentCard);
        assertEquals("Shield", card.getName());
    }

    @Test
    void testCreatePowerEquipment() {
        Card card = CardFactory.createPowerEquipment("Ring of Power", 2, 2);
        assertNotNull(card);
        assertTrue(card instanceof EquipmentCard);
        assertEquals("Ring of Power", card.getName());
    }
}