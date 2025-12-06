# TCG Card Game

**Team:** Nate Cowan
**Language:** Java

A Java-based Trading Card Game inspired by Magic: The Gathering, featuring turn-based combat, creature management, and AI opponents.

## Project Information

- **Project Name:** TCG Card Game
- **Team Member:** Nate Cowan
- **Development Language:** Java
- **Description:** A strategy card game where players summon creatures, cast spells, and battle opponents. Players draw cards, play up to 3 cards per turn, and use creatures to attack and defend until one player's health reaches zero or runs out of cards.

## Design Patterns Implemented

This project demonstrates **5 design patterns** as required:

### 1. **Template Method Pattern** (AI Players)
- `AbstractAIPlayer` defines the template for AI turn behavior
- `playTurn()` method provides the algorithm structure
- Subclasses (`RandomAIPlayer`, `AggressiveAIPlayer`) implement `chooseCardToPlay()` to customize behavior

### 2. **Strategy Pattern** (Effects)
- Different card effects are handled polymorphically

### 3. **Observer Pattern** (EventManager)
- Game events are broadcast to observers
- Events include: creature summoned, player damaged, card played, etc.

### 4. **Factory Pattern** (CardFactory & DeckBuilder)
- `CardFactory` creates different types of cards
- `DeckBuilder` constructs complete decks using the factory

### 5. **Decorator Pattern** (Equipment)
- Equipment is equiped to creatures using decorator pattern

## Game Rules

### Turn Structure (Magic: The Gathering Style)
1. **Untap Phase** - Untap all creatures, remove damage, remove summoning sickness
2. **Draw Phase** - Draw one card from deck
3. **Main Phase 1** - Play up to 3 cards (creatures or effects)
4. **Combat Phase**
    - Declare attackers (untapped creatures without summoning sickness)
    - Declare blockers (untapped creatures)
    - Resolve combat (simultaneous damage)
5. **Main Phase 2** - Play more cards (if under 3 card limit)
6. **End Phase** - Discard down to 7 cards if needed

### Card Types
- **Creature Cards** - Can attack and block, have power/toughness (damage/health)
- **Effect Cards** - Instant effects like damage, healing, card draw
- **Equipment Cards** - Equipped to creatures to give buffs

### Creature Mechanics
- **Summoning Sickness** - Creatures can't attack the turn they're played
- **Tapping** - Creatures tap when attacking or blocking
- **Combat Damage** - Damage accumulates during combat and clears at start of turn
- **Death** - Creatures with 0 or less health are removed from battlefield

### Win Conditions
- Reduce opponent's health to 0
- Opponent runs out of cards (deck + hand empty)

## AI Opponents

### RandomAIPlayer
- Plays random cards from hand
- Attacks with all available creatures
- Blocks with first available creature

### AggressiveAIPlayer
- Prioritizes damage effects when opponent is low HP (< 10)
- Prefers playing creatures for board presence
- Falls back to other effects or first card in hand
- Attacks aggressively with all creatures

## Future Enhancements

Potential features for future development:
- Mana system for casting costs
- More card types
- Special abilities (flying, first strike, trample)
- Deck customization
- More players
- GUI interface
- Save/load game state
- Card rarity and deck building restrictions
