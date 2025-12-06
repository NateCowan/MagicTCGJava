package main.TCG.Card.Equipment;

import main.TCG.Card.ICreature;

public class BuffDamageDecorator extends CreatureDecorator {
    private int buffAmount;
    public BuffDamageDecorator(ICreature creature, int buffAmount) { super(creature); this.buffAmount = buffAmount; }

    @Override
    public int getDamage() { return super.getDamage() + buffAmount; }
}