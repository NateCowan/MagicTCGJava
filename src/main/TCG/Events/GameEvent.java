package main.TCG.Events;

import main.TCG.Player;

public class GameEvent {
    private EventType type;
    private Player source;
    private Player target;
    private Object data;

    public GameEvent(EventType type, Player source, Player target, Object data) {
        this.type = type; this.source = source; this.target = target; this.data = data;
    }

    public EventType getType() { return type; }
    public Player getSource() { return source; }
    public Player getTarget() { return target; }
    public Object getData() { return data; }
}