package main.TCG.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckBuilder {

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

    public static List<Card> buildDeck(int totalCards) {
        List<Card> deck = new ArrayList<>();

        for (int i = 0; i < totalCards; i++) {
            if (random.nextBoolean()) {
                deck.add(createRandomCreature());
            } else {
                deck.add(createRandomEffect());
            }
        }

        return deck;
    }

    private static CreatureCard createRandomCreature() {
        int health = random.nextInt(5) + 1;  // 1-5 HP
        int damage = random.nextInt(5) + 1;  // 1-5 Damage

        String name = creatureFirstNames[random.nextInt(creatureFirstNames.length)] + " " +
                creatureLastNames[random.nextInt(creatureLastNames.length)];

        return new CreatureCard(name, health, damage);
    }

    private static EffectCard createRandomEffect() {
        int type = random.nextInt(3);
        String name;

        switch (type) {
            case 0 -> { // Damage spell
                int dmg = random.nextInt(5) + 1;
                name = damageEffectFirstParts[random.nextInt(damageEffectFirstParts.length)] + " " +
                        damageEffectSecondParts[random.nextInt(damageEffectSecondParts.length)];
                return (EffectCard) CardFactory.createDamageSpell(name, dmg);
            }
            case 1 -> { // Heal spell
                int heal = random.nextInt(5) + 1;
                name = healEffectFirstParts[random.nextInt(healEffectFirstParts.length)] + " " +
                        healEffectSecondParts[random.nextInt(healEffectSecondParts.length)];
                return (EffectCard) CardFactory.createHealSpell(name, heal);
            }
            default -> { // Draw card spell
                int draw = random.nextInt(2) + 1;
                name = drawEffectFirstParts[random.nextInt(drawEffectFirstParts.length)] + " " +
                        drawEffectSecondParts[random.nextInt(drawEffectSecondParts.length)];
                return (EffectCard) CardFactory.createDrawSpell(name, draw);
            }
        }
    }

}