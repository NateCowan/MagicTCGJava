package main.TCG.Card;

import main.TCG.Card.Effects.EffectStrategy;
import main.TCG.Player;

public class EffectCard extends Card {
    private EffectStrategy effect;

    public EffectCard(String name, EffectStrategy effect) {
        this.name = name;
        this.effect = effect;
    }

    @Override
    public void play(Player owner, Player opponent) {
        System.out.println(owner.getName() + " plays effect card: " + name);
        effect.apply(owner, opponent);
        owner.getGame().getEventManager().notify(
                new main.TCG.Events.GameEvent(
                        main.TCG.Events.GameEvent.EventType.CARD_PLAYED,
                        owner,
                        opponent,
                        this
                )
        );
    }

    public String getEffectType() {
        return effect.getClass().getSimpleName().replace("Effect", "");
    }

    public int getEffectValue() {
        return effect.getValue();
    }
}