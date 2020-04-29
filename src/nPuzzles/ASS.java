package nPuzzles;
import java.util.*;
// A* search
class ASS extends PuzzlesAlgorithm {

    // Comparator
    private CostCompare costCmp = new CostCompare();

    public void puzzleAlgorithm (PuzzleState init, PuzzleState goal)
    {
        System.out.println ("A* Search");

        // Validate inputs
        if (init == null || goal == null) {
            System.out.println("Invalid input!");
            return;
        }

        // start counting
        timeCounter.startCounting();

        // Initialize resources
        exploredStates = new HashMap<String, PuzzleState>();
        costCmp.setGoal(goal);
        PriorityQueue<PuzzleState> queue = new PriorityQueue<PuzzleState>(costCmp);
        queue.add(init);

        while (!queue.isEmpty()) {
            // Initialize
            PuzzleState currS = queue.poll();
            //PrintState.printState(currS);

            // Check goal
            if (currS.equals(goal)) {
                System.out.println("Goal reached");

                // print time
                double timeElapsed = timeCounter.stopCounting();
                System.out.printf("%f seconds used\n\n",  timeElapsed);

                // Print actions
                PrintState.printer(currS);

                return;
            }

            // add to Explored
            //int hashcode = currS.hashCode();
            exploredStates.put(currS.toString(), currS);

            // Slide and generate the next possible state
            addValidState(PuzzleSlider.up(currS), queue);
            addValidState(PuzzleSlider.down(currS), queue);
            addValidState(PuzzleSlider.left(currS), queue);
            addValidState(PuzzleSlider.right(currS), queue);
        } // end of while

        timeCounter.stopCounting();
        System.out.println("Failed to find a solution!");
    } // end of puzzleAlgo()

    protected boolean isQueued (PuzzleState state, Object queue) {

        return ((PriorityQueue<PuzzleState>)queue).contains(state);
    }

    // Add a state after validation
    protected void addValidState (PuzzleState state, Object q) {
        if (state == null)
            return;

        // If the state exists in the queue but has higher cost
        // replace that with this state
        PriorityQueue<PuzzleState>queue = (PriorityQueue<PuzzleState>)q;
        if (isQueued(state, queue)) {
            Iterator<PuzzleState> iter =  ((PriorityQueue<PuzzleState>) q).iterator();
            while (iter.hasNext()) {
                PuzzleState iterState =  iter.next();
                if (state.equals(iterState)
                        && (costCmp.compare(state, iterState) < 0)) {
                    queue.remove (iterState);
                    queue.add (state);
                    break;
                }
            } // end of while
        }
        // If the state is not queued
        // and not explored, enqueue
        else {
           if (!isExplored(state, exploredStates)) {
               queue.add(state);
           }
        }
    }// end of addValidState
} // end of ASS

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