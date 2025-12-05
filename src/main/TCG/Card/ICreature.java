package main.TCG.Card;

public interface ICreature {
    String getName();
    int getHealth();
    int getDamage();
    void play(main.TCG.Player owner, main.TCG.Player opponent);
}