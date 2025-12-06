package test.CardTest.EffectTest;

import main.TCG.Card.DeckBuilder;
import main.TCG.Card.EffectCard;
import main.TCG.Card.Effects.DamageEffect;
import main.TCG.Card.Effects.DrawCardEffect;
import main.TCG.Card.Effects.EffectStrategy;
import main.TCG.Card.Effects.HealEffect;
import main.TCG.Game;
import main.TCG.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class EffectStrategyTest {
    private Player player;
    private Player opponent;

    @BeforeEach
    void setUp() {
        player = new Player("Player", 20);
        opponent = new Player("Opponent", 20);
        new Game(player, opponent);
    }

    @Test
    void testEffectStrategyPolymorphism() {
        // Test that different effects can be used polymorphically
        EffectStrategy damage = new DamageEffect(5);
        EffectStrategy heal = new HealEffect(3);
        EffectStrategy draw = new DrawCardEffect(2);

        player.setDeck(DeckBuilder.buildDeck(20));
        player.takeDamage(10);

        // Apply effects through interface
        damage.apply(player, opponent);
        assertEquals(15, opponent.getHealth());

        heal.apply(player, opponent);
        assertEquals(13, player.getHealth());

        int initialHand = player.getHand().size();
        draw.apply(player, opponent);
        assertEquals(initialHand + 2, player.getHand().size());
    }

    @Test
    void testEffectCardWithDifferentStrategies() {
        EffectCard damageCard = new EffectCard("Fireball", new DamageEffect(4));
        EffectCard healCard = new EffectCard("Heal", new HealEffect(6));
        EffectCard drawCard = new EffectCard("Study", new DrawCardEffect(1));

        player.setDeck(DeckBuilder.buildDeck(20));
        player.takeDamage(8);

        damageCard.play(player, opponent);
        assertEquals(16, opponent.getHealth());

        healCard.play(player, opponent);
        assertEquals(18, player.getHealth());

        int handSize = player.getHand().size();
        drawCard.play(player, opponent);
        assertEquals(handSize + 1, player.getHand().size());
    }
}