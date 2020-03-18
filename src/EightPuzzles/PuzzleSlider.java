package EightPuzzles;

public class PuzzleSlider {

    // Slide
    // up - 1
    public PuzzleState up (PuzzleState) { return slide(1); }
    // down - 2
    public PuzzleState down (){
        return slide(2);
    }
    // left - 3
    public PuzzleState left (){
        return slide(3);
    }
    // right - 4
    public PuzzleState right (){
        return slide(4);
    }

    // Util
    // check current boundary to see if the movement is feasible
    private boolean checkBoundary(int direction, PuzzleState currState) {
        switch (direction) {
            case 1: // up
                return spacePos[1] == 0? false : true;
            break;
            case 2: // down
                return spacePos[1] == 2? false : true;
            break;
            case 3: //left
                return spacePos[0] == 0? false : true;
            break;
            case 4: // right
                return spacePos[0] == 2? false : true;
            break;
        }
        return false;
    }

    // slide the puzzles towards the direction
    private PuzzleState slide (int direction, PuzzleState currState)
    {
        if (checkBoundary(direction, currState)) {
            int x = spacePos[1], y = spacePos[0];
            switch (direction) {
                case 1: // up
                    state[x][y] = state[x][y - 1];
                    state[x][y - 1] = ' ';
                    spacePos[y] -= 1;
                    break;
                case 2: // down
                    state[x][y] = state[x][y + 1];
                    state[x][y + 1] = ' ';
                    spacePos[y] += 1;
                    break;
                case 3: //left
                    state[x][y] = state[x - 1][y];
                    state[x - 1][y] = ' ';
                    spacePos[x] -= 1;
                    break;
                case 4: // right
                    state[x][y] = state[x + 1][y];
                    state[x + 1][y] = ' ';
                    spacePos[x] += 1;
                    break;
            } // end of switch
            return true;
        }// end of if

        return NULL;
    }
}
