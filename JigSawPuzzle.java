Puzzle
 ├── Piece[][]
 └── Remaining Pieces

Piece
 ├── Top Edge
 ├── Right Edge
 ├── Bottom Edge
 └── Left Edge

Edge
 └── fitsWith(...)


 And the solving algorithm would:

1. Classify pieces into corners, borders, and middle pieces.
2. Place a corner in the top-left position.
3. Fill the board left-to-right, top-to-bottom.
4. Use fitsWith() to validate neighbors.
5. Use backtracking when multiple candidates fit.

That's the level of design discussion expected for a classic interview question from the book Cracking the Coding Interview.


boolean solve(int row, int col) {

    if(board complete)
        return true;

    for(Piece p : remainingPieces) {

        if(canPlace(p,row,col)) {

            place(p);

            if(solve(nextRow,nextCol))
                return true;

            remove(p);
        }
    }

    return false;
}


//Complete code
import java.util.*;

enum Direction {
    TOP,
    RIGHT,
    BOTTOM,
    LEFT
}

enum EdgeType {
    INNER,
    OUTER,
    FLAT
}

class Edge {

    private EdgeType type;
    private String pattern;

    public Edge(EdgeType type, String pattern) {
        this.type = type;
        this.pattern = pattern;
    }

    public EdgeType getType() {
        return type;
    }

    public String getPattern() {
        return pattern;
    }

    // Assumed by the problem statement.
    public boolean fitsWith(Edge other) {

        if (other == null) {
            return false;
        }

        return Objects.equals(this.pattern, other.pattern);
    }
}

class Piece {

    private int id;
    private Edge[] edges;

    public Piece(int id,
                 Edge top,
                 Edge right,
                 Edge bottom,
                 Edge left) {

        this.id = id;

        edges = new Edge[4];

        edges[Direction.TOP.ordinal()] = top;
        edges[Direction.RIGHT.ordinal()] = right;
        edges[Direction.BOTTOM.ordinal()] = bottom;
        edges[Direction.LEFT.ordinal()] = left;
    }

    public int getId() {
        return id;
    }

    public Edge getEdge(Direction direction) {
        return edges[direction.ordinal()];
    }

    public int flatEdgeCount() {

        int count = 0;

        for (Edge edge : edges) {

            if (edge.getType() == EdgeType.FLAT) {
                count++;
            }
        }

        return count;
    }

    public void rotateClockwise() {

        Edge temp = edges[3];

        edges[3] = edges[2];
        edges[2] = edges[1];
        edges[1] = edges[0];
        edges[0] = temp;
    }
}

class Puzzle {

    private int size;

    private Piece[][] board;

    private List<Piece> remainingPieces;

    public Puzzle(int size, List<Piece> pieces) {

        this.size = size;

        this.board = new Piece[size][size];

        this.remainingPieces = new ArrayList<>(pieces);
    }

    public boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int row, int col) {

        if (row == size) {
            return true;
        }

        int nextRow = row;
        int nextCol = col + 1;

        if (nextCol == size) {
            nextRow++;
            nextCol = 0;
        }

        List<Piece> candidates =
                new ArrayList<>(remainingPieces);

        for (Piece piece : candidates) {

            for (int rotation = 0; rotation < 4; rotation++) {

                if (canPlace(piece, row, col)) {

                    board[row][col] = piece;

                    remainingPieces.remove(piece);

                    if (solve(nextRow, nextCol)) {
                        return true;
                    }

                    board[row][col] = null;

                    remainingPieces.add(piece);
                }

                piece.rotateClockwise();
            }
        }

        return false;
    }

    private boolean canPlace(Piece piece,
                             int row,
                             int col) {

        // Top boundary
        if (row == 0) {

            if (piece.getEdge(Direction.TOP)
                    .getType() != EdgeType.FLAT) {

                return false;
            }
        } else {

            Piece topPiece = board[row - 1][col];

            if (topPiece != null) {

                Edge topEdge =
                        piece.getEdge(Direction.TOP);

                Edge neighborBottom =
                        topPiece.getEdge(Direction.BOTTOM);

                if (!topEdge.fitsWith(neighborBottom)) {
                    return false;
                }
            }
        }

        // Left boundary
        if (col == 0) {

            if (piece.getEdge(Direction.LEFT)
                    .getType() != EdgeType.FLAT) {

                return false;
            }
        } else {

            Piece leftPiece = board[row][col - 1];

            if (leftPiece != null) {

                Edge leftEdge =
                        piece.getEdge(Direction.LEFT);

                Edge neighborRight =
                        leftPiece.getEdge(Direction.RIGHT);

                if (!leftEdge.fitsWith(neighborRight)) {
                    return false;
                }
            }
        }

        // Bottom boundary
        if (row == size - 1) {

            if (piece.getEdge(Direction.BOTTOM)
                    .getType() != EdgeType.FLAT) {

                return false;
            }
        }

        // Right boundary
        if (col == size - 1) {

            if (piece.getEdge(Direction.RIGHT)
                    .getType() != EdgeType.FLAT) {

                return false;
            }
        }

        return true;
    }

    public void printBoard() {

        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                if (board[row][col] == null) {
                    System.out.print("X ");
                } else {
                    System.out.print(
                            board[row][col].getId() + " ");
                }
            }

            System.out.println();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        List<Piece> pieces = new ArrayList<>();

        // Dummy puzzle pieces would be created here.

        Puzzle puzzle = new Puzzle(3, pieces);

        boolean solved = puzzle.solve();

        System.out.println("Solved = " + solved);

        puzzle.printBoard();
    }
}