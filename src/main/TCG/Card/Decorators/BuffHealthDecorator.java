package main.TCG.Card.Decorators;

import main.TCG.Card.ICreature;

public class BuffHealthDecorator extends CreatureDecorator {
    private int buffAmount;
    public BuffHealthDecorator(ICreature creature, int buffAmount) { super(creature); this.buffAmount = buffAmount; }

    @Override
    public int getHealth() { return super.getHealth() + buffAmount; }
}