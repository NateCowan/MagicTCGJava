package main.TCG.Card.Effects;

import main.TCG.Player;

public class HealEffect implements EffectStrategy {
    private int amount;

    public HealEffect(int amount) {
        this.amount = amount;
    }

    public int getValue() { return amount; }

    @Override
    public void apply(Player owner, Player opponent) {
        owner.heal(amount);
        System.out.println(owner.getName() + " heals " + amount + " HP!");
    }
}