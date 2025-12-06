package test.AITest;

import main.TCG.*;
import main.TCG.AI.*;
import main.TCG.Card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AbstractAIPlayerTest {

    @Test
    void testCannotInstantiateAbstractAIPlayer() {
        // AbstractAIPlayer should be abstract
        // This test verifies the design pattern is correctly implemented
        assertTrue(java.lang.reflect.Modifier.isAbstract(
                        AbstractAIPlayer.class.getModifiers()),
                "AbstractAIPlayer should be abstract");
    }

    @Test
    void testTemplateMethodPattern() {
        // Verify that playTurn is public (template method)
        // and chooseCardToPlay is protected (hook method)
        try {
            java.lang.reflect.Method playTurn =
                    AbstractAIPlayer.class.getMethod("playTurn", Player.class);
            assertTrue(java.lang.reflect.Modifier.isPublic(
                            playTurn.getModifiers()),
                    "playTurn should be public (template method)");

            java.lang.reflect.Method chooseCard =
                    AbstractAIPlayer.class.getDeclaredMethod("chooseCardToPlay", Player.class);
            assertTrue(java.lang.reflect.Modifier.isProtected(
                            chooseCard.getModifiers()),
                    "chooseCardToPlay should be protected (hook method)");

        } catch (NoSuchMethodException e) {
            fail("Template Method pattern not correctly implemented");
        }
    }

    @Test
    void testAIPlayersExtendAbstractAIPlayer() {
        assertTrue(AbstractAIPlayer.class.isAssignableFrom(RandomAIPlayer.class),
                "RandomAIPlayer should extend AbstractAIPlayer");
        assertTrue(AbstractAIPlayer.class.isAssignableFrom(AggressiveAIPlayer.class),
                "AggressiveAIPlayer should extend AbstractAIPlayer");
    }
}
