package nPuzzles;
import java.io.*;
import java.util.*;

public class nPuzzles {

    private static String[] algorithmList = {
            "Breadth First Search",
            "A* Search",
            "Depth First Search"};

    public static void InstantiateAlgorithm (PuzzleState initState,
                                            PuzzleState goalState,
                                            int algorithmChoice) {
        PuzzlesAlgorithm algorithm = null;
        switch (algorithmChoice) {
            case 1:
                algorithm = new BFS();
                break;
            case 2:
                algorithm = new ASS();
                break;
            case 3:
                algorithm = new DFS();
                break;
            default:
                System.out.println("try again!");
                return;
        }
        algorithm.puzzleAlgorithm(initState, goalState);
    }

    public static void Solve (PuzzleState initState, PuzzleState goalState)
    {
        //PrintState.printer(initState);
        // Choose an algorithm
        System.out.println ("Choose an search algorithm:");

        // Select an algorithm
        int i = 1;
        for (String s : algorithmList) {
            System.out.printf("%d. %s\n", i++, s);
        }
        Scanner scan = new Scanner (System.in);

        while (true) {
            System.out.print("Choose algorithm:");
            // Get input
            if (scan.hasNextInt()) {
               int choice = scan.nextInt();
               InstantiateAlgorithm(initState, goalState, choice);
               break; // from while loop
            } // end of if
            else
                System.out.println("Invalid input!");
        }// end of while
    }


}
