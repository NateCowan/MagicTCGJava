package main.TCG.Card.Equipment;
import main.TCG.Card.ICreature;

// +0/+X
public class HealthEquipment extends EquipmentDecorator {
    private int healthBonus;

    public HealthEquipment(ICreature creature, String equipmentName, int healthBonus) {
        super(creature, equipmentName);
        this.healthBonus = healthBonus;
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
