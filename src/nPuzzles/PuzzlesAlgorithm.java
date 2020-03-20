package nPuzzles;
import java.util.*;

public abstract class PuzzlesAlgorithm {
    abstract void puzzleAlgo(PuzzleState init, PuzzleState goal);
}

class PrintState {
    // print a state
    public static void  printer(PuzzleState currState) {
        char[][] refState = currState.getState();
        for (char[] line : refState) {
            for (char ch : line){
                if (ch == ' ')
                    System.out.print("# ");
                else
                    System.out.printf("%c ", ch);
            }
            System.out.println();
        }
        System.out.println();
    }
}


class CostCompare implements Comparator<PuzzleState> {

    public int compare(PuzzleState a, PuzzleState b)
    {

        return 1;
    }
}

class BreadthFirst extends PuzzlesAlgorithm {
    public void puzzleAlgo (PuzzleState init, PuzzleState goal)
    {
        // Validate inputs
        if (init.equals(goal))
            return;

        // Initialize resources
        LinkedList<PuzzleState> queue = new LinkedList<PuzzleState>();
        queue.add(init);


        while (!queue.isEmpty()) {
            // Initialize
            PuzzleState currS = queue.removeFirst();
            ArrayList<PuzzleState> nextS = new ArrayList<PuzzleState>();

            // Slide and generate the next possible state
            nextS.add(PuzzleSlider.up(currS));
            nextS.add(PuzzleSlider.down(currS));
            nextS.add(PuzzleSlider.left(currS));
            nextS.add(PuzzleSlider.right(currS));


            for (PuzzleState s : nextS) {
                // Verify state
                if (s != null) {
                    // Print an action
                    PrintState.printer(s);

                    // Check goal
                    if (s.equals(goal))
                        break;
                }
            }


        }

    }
}


