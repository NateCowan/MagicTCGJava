package test.CardTest;

import main.TCG.Card.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DeckBuilderTest {

    @Test
    void testBuildDeckCreatesCorrectSize() {
        List<Card> deck = DeckBuilder.buildDeck(100);
        assertEquals(100, deck.size());
    }

    @Test
    void testBuildDeckContainsMixOfCards() {
        List<Card> deck = DeckBuilder.buildDeck(100);

        boolean hasCreature = false;
        boolean hasEffect = false;
        boolean hasEquipment = false;

        for (Card card : deck) {
            if (card instanceof CreatureCard) hasCreature = true;
            if (card instanceof EffectCard) hasEffect = true;
            if (card instanceof EquipmentCard) hasEquipment = true;
        }

        assertTrue(hasCreature, "Deck should contain creatures");
        assertTrue(hasEffect, "Deck should contain effects");
        assertTrue(hasEquipment, "Deck should contain equipment");
    }

    @Test
    void testDeckIsShuffled() {
        List<Card> deck1 = DeckBuilder.buildDeck(50);
        List<Card> deck2 = DeckBuilder.buildDeck(50);

        // Decks should be different due to randomization
        boolean different = false;
        for (int i = 0; i < Math.min(deck1.size(), deck2.size()); i++) {
            if (!deck1.get(i).getName().equals(deck2.get(i).getName())) {
                different = true;
                break;
            }
        }
        assertTrue(different, "Decks should be randomized");
    }

    @Test
    void testAllCardsHaveNames() {
        List<Card> deck = DeckBuilder.buildDeck(50);

        for (Card card : deck) {
            assertNotNull(card.getName());
            assertFalse(card.getName().isEmpty());
        }
    }
}
