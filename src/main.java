import nPuzzles.*;

import java.util.Arrays;

public class main {
    public static void main (String[] arg) {

        char[][] startState0 = {
                {'7', '2', '4'},
                {'5', ' ', '6'},
                {'8', '3', '1'}};
        char[][] goalState = {
                {' ', '1', '2'},
                {'3', '4', '5'},
                {'6', '7', '8'}};

        char[][] startState1 = {
                {'1', '2', '5'},
                {'3', '4', ' '},
                {'6', '7', '8'}};

        PuzzleState start = new PuzzleState(startState1);
        PuzzleState goal = new PuzzleState(goalState);

        nPuzzles.Solve(start, goal);
    }
}
