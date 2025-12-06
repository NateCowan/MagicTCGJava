package main.TCG.AI;

import main.TCG.Card.Card;
import main.TCG.Card.CreatureCard;
import main.TCG.Card.EffectCard;
import main.TCG.Card.Equipment.EquipmentType;
import main.TCG.Card.EquipmentCard;
import main.TCG.Player;

import java.util.List;

public class AggressiveAIPlayer extends AbstractAIPlayer {

    public AggressiveAIPlayer(String name, int health) {
        super(name, health);
    }

    @Override
    protected Card chooseCardToPlay(Player opponent) {
        List<Card> hand = getHand();
        if (hand.isEmpty()) return null;

        // Use equipment if we have creatures on the battlefield
        if (!getBattlefield().isEmpty()) {
            for (Card card : hand) {
                if (card instanceof EquipmentCard equipment) {
                    // Prefer damage equipment for aggression
                    if (equipment.getEquipmentType().equals(EquipmentType.DAMAGE) ||
                            equipment.getEquipmentType().equals(EquipmentType.POWER) ||
                            equipment.getEquipmentType().equals(EquipmentType.FIRST_STRIKE)) {
                        return card;
                    }
                }
            }
        }

        // Play damage effects if opponent is low HP (< 10)
        if (opponent.getHealth() < 10) {
            for (Card card : hand) {
                if (card instanceof EffectCard effect) {
                    if (effect.getEffectType().equals("DAMAGE")) {
                        return card;
                    }
                }
            }
        }

        // Play creatures for board presence
        for (Card card : hand) {
            if (card instanceof CreatureCard) {
                return card;
            }
        }

        // Play any equipment (even defensive ones)
        if (!getBattlefield().isEmpty()) {
            for (Card card : hand) {
                if (card instanceof EquipmentCard) {
                    return card;
                }
            }
        }

        // Play any effect card
        for (Card card : hand) {
            if (card instanceof EffectCard) {
                return card;
            }
        }

        // Play first card in hand
        return hand.get(0);
    }
}