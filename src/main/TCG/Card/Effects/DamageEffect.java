package main.TCG.Card.Effects;

import main.TCG.Player;

public class DamageEffect implements EffectStrategy {
    private int damage;
    public DamageEffect(int damage) { this.damage = damage; }

    public int getValue() { return damage; }

    @Override
    public void apply(Player owner, Player opponent) {
        opponent.takeDamage(damage);
        System.out.println(owner.getName() + " deals " + damage + " damage to " + opponent.getName());
    }
}