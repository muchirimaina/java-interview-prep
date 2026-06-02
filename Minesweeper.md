Minesweeper: Design and implement a text-based Minesweeper game. Minesweeper is the classic
single-player computer game where an NxN grid has B mines (or bombs) hidden across the grid. The
remaining cells are either blank or have a number behind them. The numbers reflect the number of
bombs in the surrounding eight cells. The user then uncovers a cell. If it is a bomb, the player loses.
If it is a number, the number is exposed. If it is a blank cell, this cell and all adjacent blank cells (up to
and including the surrounding numeric cells) are exposed. The player wins when all non-bomb cells
are exposed. The player can also flag certain places as potential bombs. This doesn't affect game
play, other than to block the user from accidentally clicking a cell that is thought to have a bomb.


My intuition

Objects
Cell - Most important object
Bombs - Randomize generation of Bombs in the grid
GameState - Intial state, No bombs yet until user clicks a cell. Win - after all cells are revealed and bombs flagged, - Loose if a Bomb is clicked
Grid - The size of the grid used to determine how many bombs are randomly generated
Points - Points after a successful solution
User - Logged in, Record points after a successful game solution


Methods 
clickOnACell - expose number, or blank or bomb
                - for blank expose all other adjacent blank cells
                - win if all cells are exposed
                - loose if a bomb is exposed

                Cells to have two states - revealed or not revealed dependent on user activity



+----------------+
| Game           |
+----------------+
| Board board    |
| GameState state|
+----------------+
        |
        v
+----------------+
| Board          |
+----------------+
| Cell[][] grid  |
| rows           |
| cols           |
| bombs          |
+----------------+
        |
        v
+----------------+
| Cell           |
+----------------+
| bomb           |
| revealed       |
| flagged        |
| adjacentBombs  |
+----------------+

+----------------+
| GameState      |
+----------------+
| NOT_STARTED    |
| RUNNING        |
| WON            |
| LOST           |
+----------------+


I would model Minesweeper using three main classes: Cell, Board, and Game. 
Each Cell stores bomb status, reveal status, flag status, and adjacent bomb count.
The Board manages a 2D grid of cells, bomb placement, neighbor calculations, and flood-fill reveal logic.
The Game class manages game state and user actions. Revealing a blank cell uses DFS/BFS flood fill to uncover connected blank regions and bordering numbered cells. The game is won when all non-bomb cells are revealed and lost when a bomb cell is revealed.

                