package main.TCG.Card.Equipment;

import main.TCG.Card.Card;
import main.TCG.Card.ICreature;

public abstract class CreatureDecorator extends Card implements ICreature {
    protected ICreature creature;

    public CreatureDecorator(ICreature creature) { this.creature = creature; }

    @Override
    public String getName() { return creature.getName(); }
    @Override
    public int getHealth() { return creature.getHealth(); }
    @Override
    public int getDamage() { return creature.getDamage(); }
    @Override
    public void play(main.TCG.Player owner, main.TCG.Player opponent) { creature.play(owner, opponent); }
}