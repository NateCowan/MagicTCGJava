package test.EventsTest;

import main.TCG.Events.*;
import main.TCG.Events.GameEvent;
import main.TCG.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {

    // A simple test listener that records the last event it received
    private static class TestListener implements EventListener {
        GameEvent lastEvent;

        @Override
        public void onEvent(GameEvent event) {
            lastEvent = event;
        }
    }

    @Test
    void testSubscribeAndNotify() {
        EventManager manager = new EventManager();
        TestListener listener = new TestListener();

        manager.subscribe(listener);

        GameEvent event = new GameEvent(
                EventType.CREATURE_SUMMONED,
                new Player("Alice", 20),
                new Player("Bob", 20),
                "Goblin"
        );

        manager.notify(event);

        assertNotNull(listener.lastEvent);
        assertEquals(EventType.CREATURE_SUMMONED, listener.lastEvent.getType());
        assertEquals("Goblin", listener.lastEvent.getData());
    }

    @Test
    void testUnsubscribe() {
        EventManager manager = new EventManager();
        TestListener listener = new TestListener();

        manager.subscribe(listener);
        manager.unsubscribe(listener);

        GameEvent event = new GameEvent(
                EventType.PLAYER_DAMAGED,
                new Player("Alice", 20),
                new Player("Bob", 20),
                5
        );

        manager.notify(event);

        assertNull(listener.lastEvent, "Listener should not receive events after unsubscribe");
    }

    @Test
    void testMultipleListeners() {
        EventManager manager = new EventManager();

        TestListener l1 = new TestListener();
        TestListener l2 = new TestListener();

        manager.subscribe(l1);
        manager.subscribe(l2);

        GameEvent event = new GameEvent(
                EventType.CARD_PLAYED,
                new Player("Alice", 20),
                null,
                "Fireball"
        );

        manager.notify(event);

        assertNotNull(l1.lastEvent);
        assertNotNull(l2.lastEvent);
        assertEquals("Fireball", l1.lastEvent.getData());
        assertEquals("Fireball", l2.lastEvent.getData());
    }
}
