package nPuzzles;


import java.util.Arrays;

public class PuzzleState {
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
    public PuzzleState (char[][] initState) {
        // Initialize the state from input
        state = cloneState(initState);
    }

    // Comparable
    public static boolean equals (PuzzleState a, PuzzleState b) {
        return Arrays.deepEquals(a.state, b.state);
    }
    public static int hachcode (PuzzleState oth) {
        return Arrays.deepHashCode(oth.state);
    }

    // Getter
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
