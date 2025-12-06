package main.TCG.Card.Equipment;

import main.TCG.Card.ICreature;

// controller gains life equal to damage dealt
public class LifelinkEquipment extends EquipmentDecorator {
    public LifelinkEquipment(ICreature creature, String equipmentName) {
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

    public boolean hasLifelink() {
        return true;
    }
}