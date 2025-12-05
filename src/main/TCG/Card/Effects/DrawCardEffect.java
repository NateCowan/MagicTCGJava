package main.TCG.Card.Effects;

import main.TCG.Player;

public class DrawCardEffect implements EffectStrategy {
    private int count;

    public DrawCardEffect(int count) {
        this.count = count;
    }

    public int getValue() { return count; }

    @Override
    public void apply(Player owner, Player opponent) {
        for (int i = 0; i < count; i++) {
            owner.drawCard();
        }
    }
}