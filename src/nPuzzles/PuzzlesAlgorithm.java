// 1 isExplored should be placed in the algo
//   where the state is examined;
// 2 add "replace a queued state if the new one cost less"

package nPuzzles;
import java.util.*;

// Every algorithm extends this class
// so nPuzzles.solve() could choose algorithms at run-time
abstract class PuzzlesAlgorithm {
    abstract void puzzleAlgorithm(PuzzleState init, PuzzleState goal);
    HashMap<Integer, PuzzleState> exploredStates;

    static protected boolean isExplored (PuzzleState state, HashMap<Integer, PuzzleState> explored) {
        return explored.containsKey(state.hashCode());
    }
    protected abstract boolean isQueued (PuzzleState state, Object queue);
    protected abstract void addValidState (PuzzleState state, Object queue);
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
    public static void printState (PuzzleState currState)
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
        exploredStates = new HashMap<Integer, PuzzleState>();
        LinkedList<PuzzleState> stack = new LinkedList<PuzzleState>();
        stack.push(init);

        while (!stack.isEmpty()) {
            // Initialize
            PuzzleState currS = stack.pop();



            // Check goal
            if (PuzzleState.equals(currS, goal)) {
                System.out.println("\nGoal reached");

                // Print actions
                PrintState.printer(currS);

                return;
            }

            // Slide and generate the next possible state
            addValidState(PuzzleSlider.up(currS), stack);
            addValidState(PuzzleSlider.down(currS), stack);
            addValidState(PuzzleSlider.left(currS), stack);
            addValidState(PuzzleSlider.right(currS), stack);
        } // end of while
        System.out.println("Failed to find a solution!");
    }

    protected boolean isQueued (PuzzleState state, Object queue) {
        int index = ((LinkedList<PuzzleState>)queue).indexOf(state);
        return index >= 0? true : false;
    }


    private static LinkedList<Integer> tryme = new LinkedList<>();
    private static boolean isExplored (PuzzleState s) {
        int hashcode = PuzzleState.hachcode(s);
        if (tryme.contains(hashcode)) {
            System.out.println("Yes I've found that (tryme)");
            return true;
        }

        return false;
    }
    protected void addValidState (PuzzleState state, Object stack) {
        // If the state is not null, queued or explored
        // add to exploredStates and enqueue
        if (state != null
                && !isQueued(state, stack)
                && !isExplored(state)
        ) {



            //if (isExplored(state, exploredStates)) {
                //System.out.println("Yes I've found that (Explored)");

            //}

            //System.out.println("iteration:");


           //PrintState.printState(state);
            //
            //exploredStates.put(hashcode, state);
            int hashcode = PuzzleState.hachcode(state);
            if (tryme.contains(hashcode)) {
                System.out.println("Yes I've found that (tryme)");
                return;
            }
            tryme.add(PuzzleState.hachcode(state));
            ((LinkedList<PuzzleState>) stack).push(state);
        }
    }
}
