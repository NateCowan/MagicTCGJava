package main.TCG.Card;

import main.TCG.Player;

public interface ICard {
    String getName();

    void play(Player owner, Player opponent);
}
