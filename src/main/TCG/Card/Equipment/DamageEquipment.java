package main.TCG.Card.Equipment;

import main.TCG.Card.ICreature;

// +X/+0
public class DamageEquipment extends EquipmentDecorator {
    private int damageBonus;

    public DamageEquipment(ICreature creature, String equipmentName, int damageBonus) {
        super(creature, equipmentName);
        this.damageBonus = damageBonus;
    }

    @Override
    public int getDamage() {
        return equippedCreature.getDamage() + damageBonus;
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