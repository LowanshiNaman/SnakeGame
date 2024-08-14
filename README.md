ColorIdentifier 

    /*
    Assumptions :
    1. if Co-ordinates are not in the range of graph given, We are simply returning a No Colour output.
    2. We could have restricted and provide a promt of input screen itself to user, that following can be the range of co-ordinates that can be given.
     */


SnakeGame

    /*
    Data Structures and Design Patterns for a Snake Game
    Data Structures
    ArrayList<Point> for Snake Body:
    
    Purpose: Represents the snake's body as a series of coordinates.
    Operations: Efficiently handles dynamic changes to the snake's length, including adding new segments and removing the tail.
    Point for Snake and Fruit Position:
    
    Purpose: Stores the coordinates for the snake's head, tail, and fruit.
    Operations: Facilitates comparison and manipulation of positions.
    Timer for Game Loop:
    
    Purpose: Provides a periodic update mechanism for the game state and screen rendering.
    Operations: Executes game updates at regular intervals.
    JFrame and Graphics for Rendering:
    
    Purpose: Manages the game window and drawing operations.
    Operations: Handles drawing the snake, fruit, and game state updates.
    Design Patterns
    Model-View-Controller (MVC) Pattern:
    
    Model: Manages game data and logic (e.g., SnakeGame class).
    View: Renders game objects on the screen (e.g., using JFrame and Graphics).
    Controller: Handles user input and updates the game state (e.g., KeyAdapter for keyboard input).
    Observer Pattern:
    
    Purpose: Allows the Timer to periodically trigger game updates by observing the game state.
    Usage: Timer calls the actionPerformed method to update the game state.
    Strategy Pattern (Implicit):
    
    Purpose: Manages different movement directions for the snake.
    Usage: Direction-based logic in the moveSnake() method changes how the snake's position is updated.
    Singleton Pattern:
    
    Purpose: Ensures a single instance of the game window is created.
    Usage: The SnakeGame class typically represents a single game instance.
    Factory Pattern (Potential Enhancement):
    
    Purpose: Creates different game objects (e.g., different fruit types).
    Usage: Could be implemented to generate various types of game objects dynamically.
    This structured format provides a clear overview of the data structures and design patterns used in the Snake game implementation.
    */


