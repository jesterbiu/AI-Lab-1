// 1 isExplored should be placed in the algo
//   where the state is examined;
// 2 add "replace a queued state if the new one cost less"

package nPuzzles;
import java.util.*;

// Every algorithm extends this class
// so nPuzzles.solve() could choose algorithms at run-time
abstract class PuzzlesAlgorithm {
    abstract void puzzleAlgorithm(PuzzleState init, PuzzleState goal);
    HashMap<String, PuzzleState> exploredStates;
    //HashSet<PuzzleState> exploredStates;

    static protected boolean isExplored (PuzzleState state,
                                         HashMap<String, PuzzleState> explored) {
        return explored.containsKey(state.toString());
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






