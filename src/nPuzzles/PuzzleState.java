package nPuzzles;

import java.util.Arrays;

public class PuzzleState implements Comparable<PuzzleState>{
    // Parent state
    private PuzzleState parentState = null;
    int stateCost = 0;
    private static int getStateCost (PuzzleState prev) {
        if (prev == null)
            return 0;

        int cost = 0;
        while (prev != null) {
            cost++;
            prev = prev.parentState;
        }
        return cost;
    }

    // State
    private final char[][] state;
    public static char[][] cloneState(char[][] state) {
        char[][] newState = state.clone();
        for (int iLine = 0; iLine != state.length; iLine++) {
            newState[iLine] = state[iLine].clone();
        }
        return newState;
    }//char[] -> reference

    // Constructor
    // Initialize the state from input
    public PuzzleState (char[][] initState) {
        state = cloneState(initState);
    }

    public PuzzleState (char[][] initState, PuzzleState parent) {
        state = cloneState(initState);
        parentState = parent;
        stateCost = getStateCost(parentState);
    }

    public PuzzleState (PuzzleState oth) {
        this.parentState = oth.parentState;
        stateCost = getStateCost(parentState);
        state = cloneState(oth.state);
    }

    // Comparable
    public String toString()
    {
        String stateString = "";
        for (int i = 0; i != state.length; i++)
        {
            stateString += String.valueOf(state[i]);
        }
        return stateString;
    }

    public int compareTo(PuzzleState oth) {
        return toString().compareTo(oth.toString());
    }

    public boolean equals(PuzzleState oth) {
        return (this.toString().equals(oth.toString()));
    }

    public int hashCode () {
        return toString().hashCode();
    }

    // Getter
    public PuzzleState getParentState () { return parentState; }
    public int getStateCost () { return stateCost; }
    public char[][] getState () { return cloneState(state); }//return state.clone();
    public Point getSpacePos () {
        // Return the space's position
        int height = state.length;
        int width = (state[1]).length;
        for (int iLine = 0; iLine != height; iLine++) {
            for (int iCol = 0; iCol != width; iCol++) {
               if (state[iLine][iCol] == ' ') {
                   return new Point(iCol, iLine);
               }
            }
        }
        return null;
    }

}
