package main.TCG.Card;

import main.TCG.Events.EventType;
import main.TCG.Player;

public class CreatureCard extends Card implements ICreature {
    private int maxHealth;
    private int currentHealth;
    private int damage;
    private boolean tapped;
    private int damageMarkedThisTurn; // Damage accumulates during combat
    private boolean summoningSickness;

    public CreatureCard(String name, int health, int damage) {
        this.name = name;
        this.maxHealth = health;
        this.currentHealth = health;
        this.damage = damage;
        this.tapped = false;
        this.damageMarkedThisTurn = 0;
        this.summoningSickness = true;
    }

    // Tapped state management
    public boolean isTapped() {
        return tapped;
    }

    public void setTapped(boolean tapped) {
        this.tapped = tapped;
    }

    public void takeCombatDamage(int amount) {
        this.damageMarkedThisTurn += amount;
        System.out.println("  " + name + " takes " + amount + " damage");
    }

    public int getCurrentHealth() {
        return Math.max(0, currentHealth - damageMarkedThisTurn);
    }

    public boolean isAlive() {
        return getCurrentHealth() > 0;
    }

    public void startOfTurn() {
        this.tapped = false;
        this.summoningSickness = false;
        this.damageMarkedThisTurn = 0;
    }

    public boolean canAttack() {
        return !tapped && !summoningSickness;
    }

    public boolean canBlock() {
        return !tapped;
    }

    public boolean hasSummoningSickness() {
        return summoningSickness;
    }

    @Override
    public int getHealth() {
        return maxHealth;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void play(Player owner, Player opponent) {
        owner.getBattlefield().add(this);
        System.out.println(owner.getName() + " summons " + name + " (" + damage + "/" + maxHealth + ")");
        owner.getGame().getEventManager().notify(
                new main.TCG.Events.GameEvent(
                        EventType.CREATURE_SUMMONED,
                        owner, opponent, this
                )
        );
    }

    @Override
    public String toString() {
        String status = "";
        if (tapped) status += " [TAPPED]";
        if (summoningSickness) status += " [Summoning Sickness]";
        if (damageMarkedThisTurn > 0) status += " [" + damageMarkedThisTurn + " dmg marked]";

        return name + " (" + damage + "/" + getCurrentHealth() + ")" + status;
    }
}