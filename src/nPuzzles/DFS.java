
package nPuzzles;

import java.util.HashMap;
import java.util.LinkedList;


// Depth First Search
class DFS extends PuzzlesAlgorithm {

    public void puzzleAlgorithm (PuzzleState init, PuzzleState goal) {
        System.out.println ("Depth First Search");

        // Validate inputs
        if (init == null || goal == null) {
            System.out.println("Invalid input!");
            return;
        }

        // Initialize resources
        exploredStates = new HashMap<String, PuzzleState>();
        LinkedList<PuzzleState> stack = new LinkedList<PuzzleState>();
        stack.push(init);

        while (!stack.isEmpty()) {
            // Initialize
            PuzzleState currS = stack.pop();

            // test print
            //System.out.println(currS.hashCode());

            // Check goal
            if (currS.equals(goal)) {
                System.out.println("\nGoal reached");

                // Print actions
                PrintState.printer(currS);

                return;
            }

            // add to Explored
            int hashcode = currS.hashCode();
            exploredStates.put(currS.toString(), currS);


            // Slide and generate the next possible state
            addValidState(PuzzleSlider.up(currS), stack);
            addValidState(PuzzleSlider.down(currS), stack);
            addValidState(PuzzleSlider.left(currS), stack);
            addValidState(PuzzleSlider.right(currS), stack);
        } // end of while
        System.out.println("Failed to find a solution!");
    }

    protected boolean isQueued (PuzzleState state, Object queue) {
        return ((LinkedList<PuzzleState>)queue).contains(state);
    }

    protected void addValidState (PuzzleState state, Object stack) {
        // If the state is not null, queued or explored
        // add to exploredStates and enqueue
        if (state == null)
            return;
        if (!isQueued(state, stack)
                && !isExplored(state, exploredStates)
        )
            ((LinkedList<PuzzleState>) stack).push(state);
    }
}
