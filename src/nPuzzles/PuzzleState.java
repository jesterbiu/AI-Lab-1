package nPuzzles;


import java.util.Arrays;

public class PuzzleState {
    // Parent state
    private PuzzleState parentState = null;

    // State
    private final char[][] state;
    public static char[][] cloneState(char[][] state) {
        char[][] newState = state.clone();
        for (int iLine = 0; iLine != state.length; iLine++) {
            newState[iLine] = state[iLine].clone();
        }
        return newState;
    }

    // Constructor
    // Initialize the state from input
    public PuzzleState (char[][] initState) {
        state = cloneState(initState);
    }

    public PuzzleState (char[][] initState, PuzzleState parent) {
        state = cloneState(initState);
        parentState = parent;
    }

    public PuzzleState (PuzzleState oth) {
        parentState = oth.parentState;
        state = cloneState(oth.state);
    }

    // Comparable
    public static boolean equals (PuzzleState a, PuzzleState b) {
        return Arrays.deepEquals(a.state, b.state);
    }
    public static int hachcode (PuzzleState oth) {
        return Arrays.deepHashCode(oth.state);
    }

    // Getter
    public PuzzleState getParentState() { return parentState; }
    public char[][] getState() { return cloneState(state); }
    public Point getSpacePos() {
        // Return the space's position
        int height = state.length;
        int width = (state[1]).length;
        for (int iLine = 0; iLine != height; iLine++) {
            for (int iCol = 0; iCol != width; iCol++) {
               if (state[iLine][iCol] == ' ') {
                   return new Point(iCol, iLine);
               }
            }
        }
        return null;
    }

}
