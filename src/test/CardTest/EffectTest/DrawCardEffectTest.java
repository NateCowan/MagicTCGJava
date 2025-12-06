package test.CardTest.EffectTest;

import main.TCG.Card.CardFactory;
import main.TCG.Card.DeckBuilder;
import main.TCG.Card.EffectCard;
import main.TCG.Card.Effects.DrawCardEffect;
import main.TCG.Game;
import main.TCG.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DrawCardEffectTest {
    private Player player;
    private Player opponent;
    private DrawCardEffect drawEffect;
    private EffectCard drawCard;

    @BeforeEach
    void setUp() {
        player = new Player("Player", 20);
        opponent = new Player("Opponent", 20);
        new Game(player, opponent);

        // Give player a deck
        player.setDeck(DeckBuilder.buildDeck(50));

        drawEffect = new DrawCardEffect(3);
        drawCard = (EffectCard) CardFactory.createDrawSpell("Insight", 3);
    }

    @Test
    void testDrawEffectApply() {
        int initialHandSize = player.getHand().size();
        drawEffect.apply(player, opponent);
        Assertions.assertEquals(initialHandSize + 3, player.getHand().size());
    }

    @Test
    void testDrawEffectGetValue() {
        Assertions.assertEquals(3, drawEffect.getValue());
    }

    @Test
    void testDrawEffectCard() {
        int initialHandSize = player.getHand().size();
        drawCard.play(player, opponent);
        Assertions.assertEquals(initialHandSize + 3, player.getHand().size());
    }

    @Test
    void testDrawEffectWithDifferentValues() {
        DrawCardEffect drawOne = new DrawCardEffect(1);
        DrawCardEffect drawFive = new DrawCardEffect(5);

        Assertions.assertEquals(1, drawOne.getValue());
        Assertions.assertEquals(5, drawFive.getValue());

        int initialSize = player.getHand().size();

        drawOne.apply(player, opponent);
        Assertions.assertEquals(initialSize + 1, player.getHand().size());

        drawFive.apply(player, opponent);
        Assertions.assertEquals(initialSize + 6, player.getHand().size());
    }

    @Test
    void testDrawFromNearEmptyDeck() {
        // Clear deck except for 2 cards
        while (player.getDeck().size() > 2) {
            player.getDeck().remove(0);
        }

        int initialHandSize = player.getHand().size();
        drawEffect.apply(player, opponent); // Try to draw 3, only 2 available

        // Should draw whatever is available
        Assertions.assertTrue(player.getHand().size() > initialHandSize);
        Assertions.assertEquals(0, player.getDeck().size());
    }
}