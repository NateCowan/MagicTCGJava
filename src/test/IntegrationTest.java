package test;

import main.TCG.*;
import main.TCG.AI.*;
import main.TCG.Card.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    void testFullGameFlow() {
        Player player = new Player("Human", 20);
        RandomAIPlayer ai = new RandomAIPlayer("AI", 20);
        Game game = new Game(player, ai);

        List<Card> deck1 = DeckBuilder.buildDeck(30);
        List<Card> deck2 = DeckBuilder.buildDeck(30);
        player.setDeck(deck1);
        ai.setDeck(deck2);

        // Draw starting hands
        for (int i = 0; i < 7; i++) {
            player.drawCard();
            ai.drawCard();
        }

        assertFalse(game.isOver());
        assertEquals(7, player.getHand().size());
        assertEquals(7, ai.getHand().size());
    }

    @Test
    void testEquipmentPersistsOnCreature() {
        Player player = new Player("Player", 20);
        Player opponent = new Player("Opponent", 20);
        new Game(player, opponent);

        CreatureCard creature = new CreatureCard("Dragon", 5, 3);
        creature.play(player, opponent);

        assertEquals(1, player.getBattlefield().size());
        assertEquals(3, creature.getDamage());

        // Simulate equipment (in real game, EquipmentCard does this)
        ICreature equipped = new main.TCG.Card.Equipment.DamageEquipment(
                creature, "Sword", 2
        );
        player.getBattlefield().set(0, equipped);

        assertEquals(5, equipped.getDamage());
        assertEquals(5, equipped.getHealth());
    }

    @Test
    void testCreatureDeathRemovesEquipment() {
        Player player = new Player("Player", 20);
        Player opponent = new Player("Opponent", 20);
        Game game = new Game(player, opponent);

        CreatureCard creature = new CreatureCard("Knight", 3, 2);
        creature.play(player, opponent);

        ICreature equipped = new main.TCG.Card.Equipment.DamageEquipment(
                creature, "Sword", 2
        );
        player.getBattlefield().set(0, equipped);

        // Deal lethal damage
        creature.takeCombatDamage(5);
        assertFalse(creature.isAlive());

        // Remove dead creatures (equipment goes with it)
        player.getBattlefield().removeIf(c -> {
            if (c instanceof CreatureCard) {
                return !((CreatureCard) c).isAlive();
            } else if (c instanceof main.TCG.Card.Equipment.EquipmentDecorator) {
                return !((main.TCG.Card.Equipment.EquipmentDecorator) c).isAlive();
            }
            return false;
        });

        assertEquals(0, player.getBattlefield().size());
    }
}
