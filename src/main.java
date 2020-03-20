import nPuzzles.*;
public class main {
    public static void main (String[] arg) {
        char[][] startState = {
                {'7', '2', '4'},
                {'5', ' ', '6'},
                {'8', '3', '1'}};
        PuzzleState start = new PuzzleState(startState);

        char[][] goalState = {
                {' ', '1', '2'},
                {'3', '4', '5'},
                {'7', '7', '8'}};
        PuzzleState goal = new PuzzleState(goalState);

        nPuzzles.Solve(start, goal);
    }
}
