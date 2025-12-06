package test.CardTest.EffectTest;

import main.TCG.Card.CardFactory;
import main.TCG.Card.EffectCard;
import main.TCG.Card.Effects.DamageEffect;
import main.TCG.Game;
import main.TCG.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DamageEffectTest {
    private Player player;
    private Player opponent;
    private DamageEffect damageEffect;
    private EffectCard damageCard;

    @BeforeEach
    void setUp() {
        player = new Player("Player", 20);
        opponent = new Player("Opponent", 20);
        new Game(player, opponent);
        damageEffect = new DamageEffect(5);
        damageCard = (EffectCard) CardFactory.createDamageSpell("Fireball", 5);
    }

    @Test
    void testDamageEffectApply() {
        Assertions.assertEquals(20, opponent.getHealth());
        damageEffect.apply(player, opponent);
        Assertions.assertEquals(15, opponent.getHealth());
    }

    @Test
    void testDamageEffectGetValue() {
        Assertions.assertEquals(5, damageEffect.getValue());
    }

    @Test
    void testDamageEffectCard() {
        Assertions.assertEquals(20, opponent.getHealth());
        damageCard.play(player, opponent);
        Assertions.assertEquals(15, opponent.getHealth());
    }

    @Test
    void testDamageEffectWithDifferentValues() {
        DamageEffect smallDamage = new DamageEffect(2);
        DamageEffect largeDamage = new DamageEffect(10);

        Assertions.assertEquals(2, smallDamage.getValue());
        Assertions.assertEquals(10, largeDamage.getValue());

        smallDamage.apply(player, opponent);
        Assertions.assertEquals(18, opponent.getHealth());

        largeDamage.apply(player, opponent);
        Assertions.assertEquals(8, opponent.getHealth());
    }

    @Test
    void testDamageCanKillPlayer() {
        DamageEffect lethalDamage = new DamageEffect(25);
        lethalDamage.apply(player, opponent);
        Assertions.assertEquals(-5, opponent.getHealth());
    }
}
