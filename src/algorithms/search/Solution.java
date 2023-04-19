package algorithms.search;
import java.util.ArrayList;
import java.util.Stack;



public class Solution {

    private ArrayList<AState> solutionPath;

    public Solution(AState goalState){
        this.solutionPath = new ArrayList<>();
        if (goalState != null){
            Stack<AState> stateStack = new Stack<>();
            AState state = goalState;
            while (state != null){
                stateStack.push(state);
                state = state.getCameFrom();
            }

            while (!stateStack.empty())
                solutionPath.add(stateStack.pop());
        }

    }

    public ArrayList<AState> getSolutionPath(){
        return this.solutionPath;
    }

}
