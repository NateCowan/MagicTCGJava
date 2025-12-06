package main.TCG.Card.Equipment;

import main.TCG.Card.ICreature;

// +X/+X
public class PowerEquipment extends EquipmentDecorator {
    private int damageBonus;
    private int healthBonus;

    public PowerEquipment(ICreature creature, String equipmentName, int damageBonus, int healthBonus) {
        super(creature, equipmentName);
        this.damageBonus = damageBonus;
        this.healthBonus = healthBonus;
    }

    @Override
    public int getDamage() {
        return equippedCreature.getDamage() + damageBonus;
    }

    @Override
    public int getHealth() {
        return equippedCreature.getHealth() + healthBonus;
    }

    @Override
    public String getName() {
        return equippedCreature.getName() + " [Equipped: " + equipmentName + "]";
    }

    @Override
    public String toString() {
        String baseStr = equippedCreature.toString();
        return baseStr.replace(equippedCreature.getName(), getName());
    }
}