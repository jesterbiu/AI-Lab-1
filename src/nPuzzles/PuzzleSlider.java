package nPuzzles;

public class PuzzleSlider {

    // Slide the puzzle towards the direction
    // and return a child state
    // up - 1
    public static PuzzleState up (PuzzleState s) { return slide(1, s); }
    // down - 2
    public static PuzzleState down (PuzzleState s){
        return slide(2, s);
    }
    // left - 3
    public static PuzzleState left (PuzzleState s){
        return slide(3, s);
    }
    // right - 4
    public static PuzzleState right (PuzzleState s){
        return slide(4, s);
    }


    // Check current boundary to see if the movement is feasible
    private static boolean checkBoundary(int direction, PuzzleState currState) {
        int[] spacePos = currState.getSpacePos();
        switch (direction) {
            case 1: // up
                return spacePos[1] == 0? false : true;
            case 2: // down
                int length = currState.getState().length;
                return spacePos[1] == length? false : true;

            case 3: //left
                return spacePos[0] == 0? false : true;

            case 4: // right
                int height = ((currState.getState())[1]).length;
                return spacePos[0] == height? false : true;
        }
        return false;
    }

    // Slide the puzzle towards the direction
    private static PuzzleState slide (int direction, PuzzleState currState)
    {
        int[] spacePos = currState.getSpacePos();
        if (checkBoundary(direction, currState)) {

            // Initialize data for sliding
            // Record space's position
            int x = spacePos[1], y = spacePos[0];
            // Copy state
            char[][] state = currState.getState().clone();

            // Slide
            switch (direction) {
                case 1: // up
                    state[y][x] = state[y - 1][x];
                    state[y - 1][x] = ' ';
                    break;
                case 2: // down
                    state[y][x] = state[y + 1][x];
                    state[y + 1][x] = ' ';
                    break;
                case 3: //left
                    state[y][x] = state[y][x - 1];
                    state[y][x - 1] = ' ';
                    break;
                case 4: // right
                    state[y][x] = state[y][x + 1];
                    state[y][x + 1] = ' ';
                    break;
            } // end of switch
            PuzzleState xxx = new PuzzleState(state);
            PrintState.printer(xxx);
            return (xxx);
        }// end of if

        // If the boundary crossed or the direction is illegal,
        return null;
    }
}
