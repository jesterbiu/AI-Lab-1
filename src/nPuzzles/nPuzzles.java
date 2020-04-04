package nPuzzles;
import java.io.*;
import java.util.*;

public class nPuzzles {

    private static String[] algorithmList = {
            "Breadth First Search",
            "A* Search",
            "Depth First Search"};


    public static void Solve(PuzzleState initState, PuzzleState goalState)
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
        PuzzlesAlgorithm algorithm = null;
        while (true) {
            System.out.print("Choose algorithm:");
            // Get input

            if (scan.hasNextInt()) {
               int choice = scan.nextInt();
               switch (choice) {
                   case 1:
                       algorithm = new BFS();
                       break;
                   case 2:
                       algorithm = new ASS();
                       break;
                   case 3:
                       //algorithm = new DFS();
                       break;
                   default:
                       break;
               }
                   break; // from while loop
            } // end of if
            else
                System.out.println("Invalid input!");
        }// end of while

        // Solve and print actions
        if (algorithm != null)
            algorithm.puzzleAlgorithm(initState, goalState);
        else
            System.out.println("Algorithm not initialized!");
    }


}
