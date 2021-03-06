import nPuzzles.*;

import java.util.*;

public class main {

    void selectSolvePuzzle (PuzzleState[] inits, PuzzleState goal) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("choose a start state(0 or 1): ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (0 <= choice && choice < inits.length) {
                    nPuzzles.Solve(inits[choice], goal);
                    continue;
                }
            }
            System.out.println("try again!");
        } // end of while
    }

    public static void main (String[] arg) {

        char[][] startState0 = {
                {'1', '2', '5'},
                {'3', '4', ' '},
                {'6', '7', '8'}};

        char[][] startState1 = {
                {'7', '2', '4'},
                {'5', ' ', '6'},
                {'8', '3', '1'}};

        char[][] goalState0 = {
                {' ', '1', '2'},
                {'3', '4', '5'},
                {'6', '7', '8'}};

        //------------------------------------

        char[][] startState2 = {
                {'1', '3', '4'},
                {'8', ' ', '5'},
                {'7', '2', '6'}};

        char[][] startState3 = {
                {'1', '3', '4'},
                {'8', '6', '2'},
                {' ', '7', '5'}};

        char[][] goalState1 = {
                {'1', '2', '3'},
                {'8', ' ', '4'},
                {'7', '6', '5'}};



        PuzzleState start0 = new PuzzleState(startState0);
        PuzzleState start1 = new PuzzleState(startState1);
        PuzzleState start2 = new PuzzleState(startState2);
        PuzzleState start3 = new PuzzleState(startState3);
        //PuzzleState start4 = new PuzzleState(startState4);
        PuzzleState goal0 = new PuzzleState(goalState0);
        PuzzleState goal1 = new PuzzleState(goalState1);

        int bfs = 1, ass = 2, dfs = 3;
        //nPuzzles.InstantiateAlgorithm(start0, goal0, bfs);
        //nPuzzles.InstantiateAlgorithm(start0, goal0, ass);
        //nPuzzles.InstantiateAlgorithm(start0, goal0, dfs);

        //nPuzzles.InstantiateAlgorithm(start1, goal0, bfs);
        //nPuzzles.InstantiateAlgorithm(start1, goal0, ass);
        //nPuzzles.InstantiateAlgorithm(start1, goal0, dfs);


        //nPuzzles.InstantiateAlgorithm(start2, goal1, bfs);
        nPuzzles.InstantiateAlgorithm(start2, goal1, ass);
        //nPuzzles.InstantiateAlgorithm(start2, goal1, dfs);

        //nPuzzles.InstantiateAlgorithm(start3, goal1, bfs);
        nPuzzles.InstantiateAlgorithm(start3, goal1, ass);
        //nPuzzles.InstantiateAlgorithm(start3, goal1, dfs);


    } // end of main
}
