package nPuzzles;
import java.io.*;

public class nPuzzles {

    public static void Solve(PuzzleState initState, PuzzleState goalState)
    {
        //PrintState.printer(initState);
        // Choose an algorithm
        System.out.println ("Choose an search algorithm:");
        System.out.println ("1. Breadth First");

        // Select an algorithm
        PuzzlesAlgorithm algo = new BreadthFirst();

        // Solve and print actions
        algo.puzzleAlgo(initState, goalState);
    }


}
