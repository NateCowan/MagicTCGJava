# TCG Card Game

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

### 5. **Decorator Pattern** (Buffs)
- 

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

## Code Architecture

### Key Classes

**Game.java**
- Main game loop and turn management
- Handles all game phases
- Manages combat resolution
- Enforces game rules

**Player.java**
- Tracks health, hand, deck, and battlefield
- Manages drawing cards and taking damage
- Contains game state for each player

**CreatureCard.java**
- Implements creature mechanics (tapping, combat, summoning sickness)
- Tracks health and damage
- Handles combat damage accumulation

**AbstractAIPlayer.java**
- Template for AI behavior
- Manages card playing logic
- Extensible for new AI strategies

**EventManager.java**
- Observer pattern implementation
- Broadcasts game events to registered listeners
- Enables loose coupling between game components

**CardFactory.java & DeckBuilder.java**
- Factory pattern for card creation
- Builds decks with randomized cards
- Centralizes card generation logic

## How to Run

1. **Compile the project:**
   ```bash
   javac -d bin src/main/TCG/**/*.java
   ```

2. **Run the game:**
   ```bash
   java -cp bin main.TCG.Main
   ```

3. **Gameplay:**
    - Follow on-screen prompts
    - Choose cards to play (1-3 per turn)
    - Select creatures to attack with
    - Declare blockers for incoming attacks
    - Battle until one player wins!

## Game Features

- ✅ **Turn-based gameplay** with MTG-style phases
- ✅ **Strategic combat** with attacking and blocking
- ✅ **AI opponents** with different playstyles
- ✅ **Card limit** (3 cards per turn) for balanced gameplay
- ✅ **Summoning sickness** prevents instant attacks
- ✅ **Hand size limit** (7 cards maximum)
- ✅ **Event system** for extensibility
- ✅ **Console-based UI** with clear game state display

## Future Enhancements

Potential features for future development:
- Mana system for casting costs
- More card types (instants, enchantments, artifacts)
- Special abilities (flying, first strike, trample)
- Deck customization
- Multiplayer support (3+ players)
- GUI interface with Swing or JavaFX
- Save/load game state
- Card rarity and deck building restrictions

## Code Design Principles

### Object-Oriented Principles Applied
- **Abstraction** - Card base class with abstract methods
- **Polymorphism** - Cards treated polymorphically, no large switch statements
- **Encapsulation** - Private fields with public methods
- **Inheritance** - AI players extend abstract base class
- **Dependency Injection** - Dependencies passed via constructors
- **Code to Abstractions** - Use interfaces (ICreature) and abstract classes

### No Large Conditionals
The codebase avoids large if-else or switch statements by using:
- Polymorphic method calls on card objects
- Strategy pattern for different behaviors
- Template method for AI decision-making

## License

Educational project for demonstrating design patterns and OOP principles.

---

**Developed by:** Nate Cowan  
**Course Project:** Object-Oriented Design Patterns  
**Language:** Java