package main.TCG.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckBuilder {
    private static int HEALTH = 5;
    private static int DAMAGE = 5;
    private static int DRAW = 2;

    private static final Random random = new Random();

    // Creature name parts
    private static final String[] creatureFirstNames = {
            "Goblin", "Orc", "Elf", "Dragon", "Troll", "Gnome", "Vampire", "Wolf", "Bear", "Knight"
    };
    private static final String[] creatureLastNames = {
            "Slayer", "Fang", "Claw", "Bane", "Crusher", "Ripper", "Stalker", "Hunter", "Bringer", "Shadow"
    };

    // Damage effect name parts
    private static final String[] damageEffectFirstParts = {
            "Fire", "Ice", "Lightning", "Shadow", "Holy", "Poison", "Wind", "Earth", "Water", "Arcane", "Blood", "Frost", "Storm", "Flame", "Thunder"
    };
    private static final String[] damageEffectSecondParts = {
            "Blast", "Strike", "Bolt", "Wave", "Curse", "Nova", "Surge", "Flare", "Storm", "Touch", "Explosion", "Slash", "Spike", "Barrage", "Impact"
    };

    // Heal effect name parts
    private static final String[] healEffectFirstParts = {
            "Divine", "Sacred", "Soothing", "Mystic", "Nature", "Blessed", "Celestial", "Light", "Holy", "Purity", "Healing", "Vital", "Restoring", "Radiant", "Serene"
    };
    private static final String[] healEffectSecondParts = {
            "Touch", "Wave", "Light", "Blessing", "Aura", "Surge", "Gift", "Flow", "Pulse", "Beam", "Touchstone", "Healing", "Grace", "Radiance", "Essence"
    };

    // Draw effect name parts
    private static final String[] drawEffectFirstParts = {
            "Insight", "Knowledge", "Clarity", "Mind", "Focus", "Vision", "Arcane", "Scholar's", "Mystic", "Enlightened", "Divine", "Sage", "Rune", "Wisdom", "Thought"
    };
    private static final String[] drawEffectSecondParts = {
            "Revelation", "Draw", "Scroll", "Insight", "Study", "Tome", "Lesson", "Message", "Secret", "Script", "Pages", "Guidance", "Cipher", "Codex", "Lore"
    };

    // Equipment name parts
    private static final String[] equipmentPrefixes = {
            "Sword", "Axe", "Shield", "Helm", "Armor", "Boots", "Gauntlets", "Ring", "Amulet", "Cloak", "Bow", "Staff", "Dagger", "Mace", "Spear"
    };
    private static final String[] equipmentSuffixes = {
            "of Power", "of Might", "of Valor", "of Glory", "of Strength", "of Defense", "of Protection",
            "of Speed", "of Fury", "of the Ancients", "of the Titans", "of Heroes", "of Champions",
            "of Dragons", "of Shadows"
    };

    public static List<Card> buildDeck(int totalCards) {
        List<Card> deck = new ArrayList<>();

        // Distribution: 45% creatures, 30% effects, 25% equipment
        int numCreatures = (int) (totalCards * 0.45);
        int numEffects = (int) (totalCards * 0.30);
        int numEquipment = totalCards - numCreatures - numEffects;

        // Add creatures
        for (int i = 0; i < numCreatures; i++) {
            deck.add(createRandomCreature());
        }

        // Add effects
        for (int i = 0; i < numEffects; i++) {
            deck.add(createRandomEffect());
        }

        // Add equipment
        for (int i = 0; i < numEquipment; i++) {
            deck.add(createRandomEquipment());
        }

        // Shuffle deck
        java.util.Collections.shuffle(deck);

        return deck;
    }

    private static CreatureCard createRandomCreature() {
        int health = random.nextInt(HEALTH) + 1;  // 1-5 HP
        int damage = random.nextInt(DAMAGE) + 1;  // 1-5 Damage

        String name = creatureFirstNames[random.nextInt(creatureFirstNames.length)] + " " +
                creatureLastNames[random.nextInt(creatureLastNames.length)];

        return new CreatureCard(name, health, damage);
    }

    private static EffectCard createRandomEffect() {
        int type = random.nextInt(3);
        String name;

        switch (type) {
            case 0 -> { // Damage spell
                int dmg = random.nextInt(DAMAGE) + 1;
                name = damageEffectFirstParts[random.nextInt(damageEffectFirstParts.length)] + " " +
                        damageEffectSecondParts[random.nextInt(damageEffectSecondParts.length)];
                return (EffectCard) CardFactory.createDamageSpell(name, dmg);
            }
            case 1 -> { // Heal spell
                int heal = random.nextInt(HEALTH) + 1;
                name = healEffectFirstParts[random.nextInt(healEffectFirstParts.length)] + " " +
                        healEffectSecondParts[random.nextInt(healEffectSecondParts.length)];
                return (EffectCard) CardFactory.createHealSpell(name, heal);
            }
            default -> { // Draw card spell
                int draw = random.nextInt(DRAW) + 1;
                name = drawEffectFirstParts[random.nextInt(drawEffectFirstParts.length)] + " " +
                        drawEffectSecondParts[random.nextInt(drawEffectSecondParts.length)];
                return (EffectCard) CardFactory.createDrawSpell(name, draw);
            }
        }
    }

    private static EquipmentCard createRandomEquipment() {
        int type = random.nextInt(6);
        String name = equipmentPrefixes[random.nextInt(equipmentPrefixes.length)] + " " +
                equipmentSuffixes[random.nextInt(equipmentSuffixes.length)];

        switch (type) {
            case 0 -> { // Damage equipment (+X/+0)
                int bonus = random.nextInt(3) + 1; // +1 to +3
                return (EquipmentCard) CardFactory.createDamageEquipment(name, bonus);
            }
            case 1 -> { // Health equipment (+0/+X)
                int bonus = random.nextInt(3) + 1; // +1 to +3
                return (EquipmentCard) CardFactory.createHealthEquipment(name, bonus);
            }
            case 2 -> { // Power equipment (+X/+X)
                int damageBonus = random.nextInt(2) + 1; // +1 to +2
                int healthBonus = random.nextInt(2) + 1; // +1 to +2
                return (EquipmentCard) CardFactory.createPowerEquipment(name, damageBonus, healthBonus);
            }
            case 3 -> { // Vigilance
                return (EquipmentCard) CardFactory.createVigilanceEquipment(name);
            }
            case 4 -> { // Lifelink
                return (EquipmentCard) CardFactory.createLifelinkEquipment(name);
            }
            default -> { // First Strike
                return (EquipmentCard) CardFactory.createFirstStrikeEquipment(name);
            }
        }
    }
}