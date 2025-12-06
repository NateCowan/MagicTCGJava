package test.CardTest.EffectTest;

import main.TCG.Card.CardFactory;
import main.TCG.Card.EffectCard;
import main.TCG.Card.Effects.HealEffect;
import main.TCG.Game;
import main.TCG.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class HealEffectTest {
    private Player player;
    private Player opponent;
    private HealEffect healEffect;
    private EffectCard healCard;

    @BeforeEach
    void setUp() {
        player = new Player("Player", 20);
        opponent = new Player("Opponent", 20);
        new Game(player, opponent);
        healEffect = new HealEffect(5);
        healCard = (EffectCard) CardFactory.createHealSpell("Healing Touch", 5);
        player.takeDamage(10); // Reduce to 10 HP
    }

    @Test
    void testHealEffectApply() {
        Assertions.assertEquals(10, player.getHealth());
        healEffect.apply(player, opponent);
        Assertions.assertEquals(15, player.getHealth());
    }

    @Test
    void testHealEffectGetValue() {
        Assertions.assertEquals(5, healEffect.getValue());
    }

    @Test
    void testHealEffectCard() {
        Assertions.assertEquals(10, player.getHealth());
        healCard.play(player, opponent);
        Assertions.assertEquals(15, player.getHealth());
    }

    @Test
    void testHealWithDifferentValues() {
        HealEffect smallHeal = new HealEffect(2);
        HealEffect largeHeal = new HealEffect(15);

        Assertions.assertEquals(2, smallHeal.getValue());
        Assertions.assertEquals(15, largeHeal.getValue());

        player.takeDamage(10); // Player at 0
        smallHeal.apply(player, opponent);
        Assertions.assertEquals(2, player.getHealth());

        largeHeal.apply(player, opponent);
        Assertions.assertEquals(17, player.getHealth());
    }
}
