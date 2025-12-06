package main.TCG.Card.Equipment;

import main.TCG.Card.ICreature;

// deals damage first in combat
public class FirstStrikeEquipment extends EquipmentDecorator {
    public FirstStrikeEquipment(ICreature creature, String equipmentName) {
        super(creature, equipmentName);
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

    public boolean hasFirstStrike() {
        return true;
    }
}