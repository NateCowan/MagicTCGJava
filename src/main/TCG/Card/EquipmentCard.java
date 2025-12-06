package main.TCG.Card;

import main.TCG.AI.AbstractAIPlayer;
import main.TCG.Card.Equipment.*;
import main.TCG.Events.EventType;
import main.TCG.Player;
import java.util.List;
import java.util.Scanner;

public class EquipmentCard extends Card {
    private EquipmentType equipmentType;
    private int damageBonus;
    private int healthBonus;

    public EquipmentCard(String name, EquipmentType equipmentType, int damageBonus, int healthBonus) {
        this.name = name;
        this.equipmentType = equipmentType;
        this.damageBonus = damageBonus;
        this.healthBonus = healthBonus;
    }

    @Override
    public void play(Player owner, Player opponent) {
        List<ICreature> battlefield = owner.getBattlefield();

        if (battlefield.isEmpty()) {
            System.out.println(owner.getName() + " has no creatures to equip!");
            System.out.println("  " + name + " is discarded.");
            return;
        }

        System.out.println(owner.getName() + " plays " + name + "!");
        System.out.println("Choose a creature to equip:");

        for (int i = 0; i < battlefield.size(); i++) {
            System.out.println("  " + (i + 1) + ": " + battlefield.get(i).toString());
        }

        int choice = getTargetChoice(owner, battlefield.size());
        ICreature target = battlefield.get(choice);

        // Apply equipment decorator
        ICreature equippedCreature = equipCreature(target);
        battlefield.set(choice, equippedCreature);

        System.out.println("  " + name + " equipped to " + target.getName() + "!");

        owner.getGame().getEventManager().notify(
                new main.TCG.Events.GameEvent(
                        EventType.CARD_PLAYED,
                        owner, opponent, this
                )
        );
    }

    private ICreature equipCreature(ICreature target) {
        switch (equipmentType) {
            case DAMAGE:
                return new DamageEquipment(target, name, damageBonus);
            case HEALTH:
                return new HealthEquipment(target, name, healthBonus);
            case POWER:
                return new PowerEquipment(target, name, damageBonus, healthBonus);
            case VIGILANCE:
                return new VigilanceEquipment(target, name);
            case LIFELINK:
                return new LifelinkEquipment(target, name);
            case FIRST_STRIKE:
                return new FirstStrikeEquipment(target, name);
            default:
                return new PowerEquipment(target, name, damageBonus, healthBonus);
        }
    }

    private int getTargetChoice(Player owner, int maxChoice) {
        if (owner instanceof AbstractAIPlayer) {
            // AI chooses strongest creature (last in list tends to be most recent/strong)
            return maxChoice - 1;
        } else {
            // Human player chooses
            Scanner scanner = new Scanner(System.in);
            int choice = -1;
            while (choice < 1 || choice > maxChoice) {
                System.out.print("Enter choice (1-" + maxChoice + "): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    scanner.next();
                }
            }
            return choice - 1;
        }
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    @Override
    public String toString() {
        String desc = name + " [Equipment: ";
        if (equipmentType == EquipmentType.DAMAGE) desc += "+" + damageBonus + " Attack";
        else if (equipmentType == EquipmentType.HEALTH) desc += "+" + healthBonus + " Health";
        else if (equipmentType == EquipmentType.POWER) desc += "+" + damageBonus + "/+" + healthBonus;
        else desc += equipmentType.name().replace("_", " ");
        desc += "]";
        return desc;
    }
}