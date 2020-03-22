package nPuzzles;
import java.util.*;

// Every algorithm extends this class
// so nPuzzles.solve() could choose algorithms at run-time
abstract class PuzzlesAlgorithm {
    abstract void puzzleAlgo(PuzzleState init, PuzzleState goal);
    HashMap<Integer, PuzzleState> exploredStates;
}


class PrintState {
    // Print actions
    public static void printer (PuzzleState goalState) {

        // Push all actions into stacks
        LinkedList<PuzzleState> stack = new LinkedList<PuzzleState>();
        PuzzleState prevState = goalState;
        while (prevState != null) {
            stack.push(prevState);
            prevState = prevState.getParentState();
        }

        // Pop actions and print
        while (!stack.isEmpty()) {
            PuzzleState aState = stack.pop();
            printState(aState);
        }
    }

    // Print a state
    private static void printState (PuzzleState currState)
    {
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

class AStar extends PuzzlesAlgorithm {
    class CostCompare implements Comparator<PuzzleState> {
        public int compare(PuzzleState a, PuzzleState b)
        {

            return 1;
        }
    }

    public void puzzleAlgo (PuzzleState init, PuzzleState goal){}
}


class BreadthFirst extends PuzzlesAlgorithm {
    public void puzzleAlgo (PuzzleState init, PuzzleState goal)
    {
        // Validate inputs
        if (init == null || goal == null) {
            System.out.println("Invalid input!");
            return;
        }

        // Initialize resources
        exploredStates = new HashMap<Integer, PuzzleState>();
        LinkedList<PuzzleState> queue = new LinkedList<PuzzleState>();
        queue.add(init);

        while (!queue.isEmpty()) {
            // Initialize
            PuzzleState currS = queue.removeFirst();

            // Check goal
            if (PuzzleState.equals(currS, goal)) {
                System.out.println("\nGoal reached");

                // Print actions
                PrintState.printer(currS);

                break;
            }

            // Slide and generate the next possible state
            addValidState(PuzzleSlider.up(currS), queue);
            addValidState(PuzzleSlider.down(currS), queue);
            addValidState(PuzzleSlider.left(currS), queue);
            addValidState(PuzzleSlider.right(currS), queue);
        } // end of while
    } // end of puzzleAlgo()


    private static boolean isQueued (PuzzleState state,
                                     LinkedList<PuzzleState> queue) {
        int index = ((LinkedList<PuzzleState>) queue).indexOf(state);
        return index >= 0? true : false;
    }

    private static boolean isExplored (PuzzleState state,
                                       HashMap<Integer, PuzzleState> explored) {
        return explored.containsKey(state.hashCode());
    }

    // Add a state after validation
    private void addValidState (PuzzleState state, Object queue) {
        // If the state is not null,
        // queued or explored
        if (state != null
            && !isQueued(state, (LinkedList<PuzzleState>)queue)
            && !isExplored(state, exploredStates)) {

            // add to exploredStates and enqueue
            int hashcode = PuzzleState.hachcode(state);
            exploredStates.put(hashcode, state);
            ((LinkedList<PuzzleState>) queue).add(state);
        }
    }


} // end of class Breadth first



