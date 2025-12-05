package main.TCG.Card;

import main.TCG.Card.Effects.*;

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

    // OR a generic effect card:
    public static Card createEffectCard(String name, EffectStrategy strategy) {
        return new EffectCard(name, strategy);
    }
}