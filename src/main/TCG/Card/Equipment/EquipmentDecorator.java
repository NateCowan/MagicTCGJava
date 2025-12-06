package main.TCG.Card.Equipment;

import main.TCG.Card.Card;
import main.TCG.Card.ICreature;
import main.TCG.Card.CreatureCard;
import main.TCG.Player;

// Decorator Pattern
public abstract class EquipmentDecorator extends Card implements ICreature {
    protected ICreature equippedCreature;
    protected String equipmentName;

    public EquipmentDecorator(ICreature creature, String equipmentName) {
        this.equippedCreature = creature;
        this.equipmentName = equipmentName;
    }

    @Override
    public String getName() {
        return equippedCreature.getName();
    }

    @Override
    public int getHealth() {
        return equippedCreature.getHealth();
    }

    @Override
    public int getDamage() {
        return equippedCreature.getDamage();
    }

    @Override
    public void play(Player owner, Player opponent) {
        equippedCreature.play(owner, opponent);
    }

    public boolean isTapped() {
        if (equippedCreature instanceof CreatureCard) {
            return ((CreatureCard) equippedCreature).isTapped();
        }
        if (equippedCreature instanceof EquipmentDecorator) {
            return ((EquipmentDecorator) equippedCreature).isTapped();
        }
        return false;
    }

    public void setTapped(boolean tapped) {
        if (equippedCreature instanceof CreatureCard) {
            ((CreatureCard) equippedCreature).setTapped(tapped);
        } else if (equippedCreature instanceof EquipmentDecorator) {
            ((EquipmentDecorator) equippedCreature).setTapped(tapped);
        }
    }

    public boolean canAttack() {
        if (equippedCreature instanceof CreatureCard) {
            return ((CreatureCard) equippedCreature).canAttack();
        } else if (equippedCreature instanceof EquipmentDecorator) {
            return ((EquipmentDecorator) equippedCreature).canAttack();
        }
        return false;
    }

    public boolean canBlock() {
        if (equippedCreature instanceof CreatureCard) {
            return ((CreatureCard) equippedCreature).canBlock();
        } else if (equippedCreature instanceof EquipmentDecorator) {
            return ((EquipmentDecorator) equippedCreature).canBlock();
        }
        return false;
    }

    public void takeCombatDamage(int amount) {
        if (equippedCreature instanceof CreatureCard) {
            ((CreatureCard) equippedCreature).takeCombatDamage(amount);
        } else if (equippedCreature instanceof EquipmentDecorator) {
            ((EquipmentDecorator) equippedCreature).takeCombatDamage(amount);
        }
    }

    public int getCurrentHealth() {
        if (equippedCreature instanceof CreatureCard) {
            return ((CreatureCard) equippedCreature).getCurrentHealth();
        } else if (equippedCreature instanceof EquipmentDecorator) {
            return ((EquipmentDecorator) equippedCreature).getCurrentHealth();
        }
        return getHealth();
    }

    public boolean isAlive() {
        if (equippedCreature instanceof CreatureCard) {
            return ((CreatureCard) equippedCreature).isAlive();
        } else if (equippedCreature instanceof EquipmentDecorator) {
            return ((EquipmentDecorator) equippedCreature).isAlive();
        }
        return true;
    }

    public void startOfTurn() {
        if (equippedCreature instanceof CreatureCard) {
            ((CreatureCard) equippedCreature).startOfTurn();
        } else if (equippedCreature instanceof EquipmentDecorator) {
            ((EquipmentDecorator) equippedCreature).startOfTurn();
        }
    }

    public ICreature getBaseCreature() {
        if (equippedCreature instanceof EquipmentDecorator) {
            return ((EquipmentDecorator) equippedCreature).getBaseCreature();
        }
        return equippedCreature;
    }

    public String getEquipmentName() {
        return equipmentName;
    }
}