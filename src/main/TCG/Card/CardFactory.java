package main.TCG.Card;

import main.TCG.Card.Effects.DamageEffect;
import main.TCG.Card.Effects.DrawCardEffect;
import main.TCG.Card.Effects.EffectStrategy;
import main.TCG.Card.Effects.HealEffect;
import main.TCG.Card.Equipment.EquipmentType;

// Factory Pattern
public class CardFactory {

    public static Card createCreature(String name, int health, int damage) {
        return new CreatureCard(name, health, damage);
    }

    public static Card createDamageSpell(String name, int damage) {
        return new EffectCard(name, new DamageEffect(damage));
    }

    public static Card createHealSpell(String name, int amount) {
        return new EffectCard(name, new HealEffect(amount));
    }

    public static Card createDrawSpell(String name, int cards) {
        return new EffectCard(name, new DrawCardEffect(cards));
    }

    // Generic effect card
    public static Card createEffectCard(String name, EffectStrategy strategy) {
        return new EffectCard(name, strategy);
    }

    // Equipment creation methods
    public static Card createDamageEquipment(String name, int damageBonus) {
        return new EquipmentCard(name, EquipmentType.DAMAGE, damageBonus, 0);
    }

    public static Card createHealthEquipment(String name, int healthBonus) {
        return new EquipmentCard(name, EquipmentType.HEALTH, 0, healthBonus);
    }

    public static Card createPowerEquipment(String name, int damageBonus, int healthBonus) {
        return new EquipmentCard(name, EquipmentType.POWER, damageBonus, healthBonus);
    }

    public static Card createVigilanceEquipment(String name) {
        return new EquipmentCard(name, EquipmentType.VIGILANCE, 0, 0);
    }

    public static Card createLifelinkEquipment(String name) {
        return new EquipmentCard(name, EquipmentType.LIFELINK, 0, 0);
    }

    public static Card createFirstStrikeEquipment(String name) {
        return new EquipmentCard(name, EquipmentType.FIRST_STRIKE, 0, 0);
    }

    // Generic equipment
    public static Card createEquipment(String name, EquipmentType type, int damageBonus, int healthBonus) {
        return new EquipmentCard(name, type, damageBonus, healthBonus);
    }
}