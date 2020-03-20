package nPuzzles;

public class PuzzleState {

    // State
    private final char[][] state;
    // Mark the space's position
    private final int[] spacePos = new int[2];

    // Constructor
    public PuzzleState (char[][] initState) {
        // Initialize the state from input
        int height = initState.length;
        int width = (initState[1]).length;
        state = initState.clone();
        for (int iLine = 0; iLine != height; iLine++) {
            for (int iCol = 0; iCol != width; iCol++) {
                state[iLine][iCol] = initState[iLine][iCol];

                // Record the space's position
                if (initState[iLine][iCol] == ' ') {
                    spacePos[1] = iLine;
                    spacePos[0] = iCol;
                }
            }
        }

    }

    // Comparable
    public boolean equals(PuzzleState oth) {
        return state.equals(oth.state);
    }

    // Getter
    public char[][] getState() { return state; }
    public int[] getSpacePos() { return spacePos; }

}
