package nPuzzles;

enum Direction {up, down, left, right}

class PuzzleSlider {

    // Slide the puzzle towards the direction
    // and return a child state
    // up - 1
    public static PuzzleState up (PuzzleState s) { return slide(Direction.up, s); }
    // down - 2
    public static PuzzleState down (PuzzleState s){
        return slide(Direction.down, s);
    }
    // left - 3
    public static PuzzleState left (PuzzleState s){
        return slide(Direction.left, s);
    }
    // right - 4
    public static PuzzleState right (PuzzleState s){
        return slide(Direction.right, s);
    }


    // Check current boundary to see if the movement is feasible
    private static boolean checkBoundary(Direction direction, PuzzleState currState, Point spacePos) {
        switch (direction) {
            case up: // up
                return spacePos.getY() == 0? false : true;
            case down: // down
                int length = currState.getState().length;
                return spacePos.getY() == length - 1? false : true;
            case left: //left
                return spacePos.getX() == 0? false : true;
            case right: // right
                int height = ((currState.getState())[1]).length;
                return spacePos.getX() == height - 1? false : true;
        }
        return false;
    }

    // Slide the puzzle towards the direction
    private static PuzzleState slide (Direction direction, PuzzleState currState)
    {
       Point spacePos = currState.getSpacePos();
        if (checkBoundary(direction, currState, spacePos)) {

            // Initialize data for sliding
            // Record space's position
            int x = spacePos.getX(), y = spacePos.getY();
            // Copy state

            char[][] state = currState.getState();

            // Slide
            switch (direction) {
                case up: // up
                    //System.out.print("up ");
                    state[y][x] = state[y - 1][x];
                    state[y - 1][x] = ' ';
                    break;
                case down: // down
                    //System.out.print("down ");
                    state[y][x] = state[y + 1][x];
                    state[y + 1][x] = ' ';
                    break;
                case left: //left
                    //System.out.println("left ");
                    state[y][x] = state[y][x - 1];
                    state[y][x - 1] = ' ';
                    break;
                case right: // right
                    //System.out.println("right ");
                    state[y][x] = state[y][x + 1];
                    state[y][x + 1] = ' ';
                    break;
            } // end of switch
            PuzzleState newState = new PuzzleState(state, currState);
            //System.out.println("Slider:");
            //PrintState.printer(newState);
            return newState;
        }// end of if

        // If the boundary crossed or the direction is illegal,
        return null;
    }
}
