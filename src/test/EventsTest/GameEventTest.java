package test.EventsTest;

import main.TCG.Events.EventType;
import main.TCG.Events.GameEvent;
import main.TCG.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEventTest {

    @Test
    void testGameEventFields() {
        Player source = new Player("Alice", 20);
        Player target = new Player("Bob", 20);

        GameEvent event = new GameEvent(
                EventType.PLAYER_DAMAGED,
                source,
                target,
                7
        );

        assertEquals(EventType.PLAYER_DAMAGED, event.getType());
        assertEquals(source, event.getSource());
        assertEquals(target, event.getTarget());
        assertEquals(7, event.getData());
    }
}
