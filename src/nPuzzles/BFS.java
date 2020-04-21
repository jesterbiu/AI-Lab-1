package nPuzzles;
import java.util.*;

// Breadth First Search
class BFS extends PuzzlesAlgorithm {
    public void puzzleAlgorithm (PuzzleState init, PuzzleState goal)
    {
        System.out.println ("Breadth First Search");

        // Validate inputs
        if (init == null || goal == null) {
            System.out.println("Invalid input!");
            return;
        }

        // Initialize resources
        exploredStates = new HashMap<String, PuzzleState>();
        LinkedList<PuzzleState> queue = new LinkedList<PuzzleState>();
        queue.add(init);

        while (!queue.isEmpty()) {
            // Initialize
            PuzzleState currS = queue.removeFirst();

            //PrintState.printState(currS);

            // Check goal
            if (currS.equals(goal)) {
                System.out.println("\nGoal reached");

                // Print actions
                PrintState.printer(currS);

                return;
            }

            // add to Explored
            exploredStates.put(currS.toString(), currS);

            // Slide and generate the next possible state
            addValidState(PuzzleSlider.up(currS), queue);
            addValidState(PuzzleSlider.down(currS), queue);
            addValidState(PuzzleSlider.left(currS), queue);
            addValidState(PuzzleSlider.right(currS), queue);
        } // end of while

        System.out.println("Failed to find a solution!");
    }


    protected boolean isQueued (PuzzleState state, Object queue) {
        return ((LinkedList<PuzzleState>)queue).contains(state);

    }


    // Add a state after validation
    protected void addValidState (PuzzleState state, Object queue) {
        // If the state is not null, queued or explored
        // add to exploredStates and enqueue
        if (state == null)
            return;
        if (!isQueued(state, queue)
                && !isExplored(state, exploredStates)
        )
            ((LinkedList<PuzzleState>) queue).add(state);
    }


} // end of class Breadth first