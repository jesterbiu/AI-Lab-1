import nPuzzles.*;

import java.util.*;

public class main {
    public static void main (String[] arg) {

        char[][] startState0 = {
                {'7', '2', '4'},
                {'5', ' ', '6'},
                {'8', '3', '1'}};

        char[][] startState1 = {
                {'1', '2', '5'},
                {'3', '4', ' '},
                {'6', '7', '8'}};

        char[][] goalState = {
                {' ', '1', '2'},
                {'3', '4', '5'},
                {'6', '7', '8'}};



        PuzzleState start0 = new PuzzleState(startState0);
        PuzzleState start1 = new PuzzleState(startState1);
        PuzzleState goal = new PuzzleState(goalState);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("choose a start state(0 or 1): ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        nPuzzles.Solve(start0, goal);
                        break;
                    case 1:
                        nPuzzles.Solve(start1, goal);
                        break;
                    default:
                        System.out.println("try again!");
                }
            } // end of switch
        } // end of while
    } // end of main
}
