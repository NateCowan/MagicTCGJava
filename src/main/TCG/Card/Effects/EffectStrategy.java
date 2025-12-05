package main.TCG.Card.Effects;

import main.TCG.Player;

public interface EffectStrategy {
    void apply(Player owner, Player opponent);

    int getValue();
}