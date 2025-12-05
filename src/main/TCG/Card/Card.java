package main.TCG.Card;

import main.TCG.Player;

public abstract class Card implements ICard {
    String name;

    public String getName() {
        return name;
    }

    public abstract void play(Player owner, Player opponent);
}