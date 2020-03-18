package EightPuzzles;

public class PuzzleState {

    // State
    private char[][] state = new char[3][3];
    // Mark the space's position
    private int[] spacePos = new int[2];

    // Constructor
    PuzzleState (char[][] initState) {
        // Initialize the state from input
        for (int iLine = 0; iLine != 3; iLine++) {
            for (int iCol = 0; iCol != 3; iCol++) {
                state[iLine][iCol] = initState[iLine][iCol];

                // Record the space's position
                if (initState[iLine][iCol] == ' ') {
                    spacePos[1] = iLine;
                    spacePos[0] = iCol;
                }
            }
        }

    }

    // Getter
    public char[][] getState() { return state; }
    public int[] getSpacePos() { return spacePos; }

}
