package main.TCG.Card.Equipment;

import main.TCG.Card.ICreature;

// doesn't tap when attacking
public class VigilanceEquipment extends EquipmentDecorator {
    public VigilanceEquipment(ICreature creature, String equipmentName) {
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

    public boolean hasVigilance() {
        return true;
    }
}