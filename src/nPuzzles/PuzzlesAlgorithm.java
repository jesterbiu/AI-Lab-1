// 1 isExplored should be placed in the algo
//   where the state is examined;
// 2 add "replace a queued state if the new one cost less"

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

class CostCompare implements Comparator<PuzzleState> {

    // Constructor
    public CostCompare () {}
    public CostCompare (PuzzleState p) {
        goal = p;
    }

    // goal
    private PuzzleState goal = null;
    // Setter
    public void setGoal (PuzzleState g) { goal = g; }

    // Methods below must be called after goal is set

    // F-cost() = G() + H();
    // G() = cost to reach the current state from the initial state
    // H() = cost to reach the goal state from the current state
    // Return the F-cost(), total cost
    private int totalCost (PuzzleState p) {
        int gCost = p.getStateCost();
        int hCost = hCost(p, goal);
        return gCost + hCost;
    }

    // Calculate the Manhattan distance as Hcost
    private int hCost (PuzzleState curr, PuzzleState goal) {
        char[][] currState = curr.getState();
        char[][] goalState = goal.getState();
        int sumDistance = 0;
        for (int y = 0; y != currState.length; y++) {
            for (int x = 0; x != currState[y].length; x++) {
                Point p = getPos (currState[y][x], goalState);
                int distance = abs(p.getX() - x) + abs (p.getY() - y);
                sumDistance += distance;
            } // end of for x
        } // end of for y

        return sumDistance;
    }

    public int compare(PuzzleState a, PuzzleState b)
    {
        return totalCost(a) - totalCost(b);
    }

    // Return ch's position in state
    // return null if not found
    private Point getPos (char ch, char[][] state) {

        // Search for chs
        for (int i = 0; i != state.length; i++) {
            for (int j = 0; j != state[i].length; j++) {
                if (state[i][j] == ch) {
                    return new Point(j, i);
                }
            } // end of for j
        } // end of for i

        // ch not found
        return null;
    }
    // Return an integer's absolute value
    private static int abs (int i) {
        return i >= 0? i : -i;
    }
}

// A* search
class ASS extends PuzzlesAlgorithm {

    // Comparator
    CostCompare costCmp = new CostCompare();

    public void puzzleAlgo (PuzzleState init, PuzzleState goal)
    {
        System.out.println ("A* Search");

        // Validate inputs
        if (init == null || goal == null) {
            System.out.println("Invalid input!");
            return;
        }

        // Initialize resources
        exploredStates = new HashMap<Integer, PuzzleState>();
        costCmp.setGoal(goal);
        PriorityQueue<PuzzleState> queue = new PriorityQueue<PuzzleState>(costCmp);
        queue.add(init);

        while (!queue.isEmpty()) {
            // Initialize
            PuzzleState currS = queue.poll();

            // Check goal
            if (PuzzleState.equals(currS, goal)) {
                System.out.println("\nGoal reached");

                // Print actions
                PrintState.printer(currS);

                return;
            }

            // Slide and generate the next possible state
            addValidState(PuzzleSlider.up(currS), queue);
            addValidState(PuzzleSlider.down(currS), queue);
            addValidState(PuzzleSlider.left(currS), queue);
            addValidState(PuzzleSlider.right(currS), queue);
        } // end of while
        System.out.println("Failed to find a solution!");
    } // end of puzzleAlgo()


    private static boolean isQueued (PuzzleState state,
                                     PriorityQueue<PuzzleState> queue) {
        return queue.contains(state);
    }

    private static boolean isExplored (PuzzleState state,
                                       HashMap<Integer, PuzzleState> explored) {
        return explored.containsKey(state.hashCode());
    }

    // Add a state after validation
    private void addValidState (PuzzleState state, Object q) {
        PriorityQueue<PuzzleState>queue = (PriorityQueue<PuzzleState>)q;
        // If the state is not null, queued or explored
        // add to exploredStates and enqueue
        if (state != null
                && !isQueued(state, queue)
                && !isExplored(state, exploredStates)) {


            int hashcode = PuzzleState.hachcode(state);
            exploredStates.put(hashcode, state);
            queue.add(state);
        }
        // If the state exists in the queue but has higher cost
        // replace that node with this state
        else if (isQueued(state, queue)) {
            Iterator<PuzzleState> iter =  ((PriorityQueue<PuzzleState>) q).iterator();
            while (iter.hasNext()) {
                PuzzleState iterState =  iter.next();
                if (PuzzleState.equals(state,iterState)
                && (costCmp.compare(state, iterState) < 0)) {
                    queue.remove (iterState);
                    queue.add (state);
                    break;
                }
            } // end of while
        } // end of if
        else { // do nothing
        }
    }// end of addValidState
} // end of ASS

// Breadth First Search
class BFS extends PuzzlesAlgorithm {
    public void puzzleAlgo (PuzzleState init, PuzzleState goal)
    {
        System.out.println ("Breadth First Search");

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

               return;
            }

            // Slide and generate the next possible state
            addValidState(PuzzleSlider.up(currS), queue);
            addValidState(PuzzleSlider.down(currS), queue);
            addValidState(PuzzleSlider.left(currS), queue);
            addValidState(PuzzleSlider.right(currS), queue);
        } // end of while

        System.out.println("Failed to find a solution!");
    } // end of puzzleAlgo()


    private static boolean isQueued (PuzzleState state,
                                     LinkedList<PuzzleState> queue) {
        int index = queue.indexOf(state);
        return index >= 0? true : false;
    }

    private static boolean isExplored (PuzzleState state,
                                       HashMap<Integer, PuzzleState> explored) {
        return explored.containsKey(state.hashCode());
    }

    // Add a state after validation
    private void addValidState (PuzzleState state, Object queue) {
        // If the state is not null, queued or explored
        // add to exploredStates and enqueue
        if (state != null
            && !isQueued(state, (LinkedList<PuzzleState>)queue)
            && !isExplored(state, exploredStates)) {

            int hashcode = PuzzleState.hachcode(state);
            exploredStates.put(hashcode, state);
            ((LinkedList<PuzzleState>) queue).add(state);
        }
    }


} // end of class Breadth first



