package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected PriorityQueue<AState> openList;
    protected HashMap<String,Boolean> visited;

    public BreadthFirstSearch() {
        super();
        this.openList = new PriorityQueue<>();
        this.visited = new HashMap<>();
    }

    @Override
    public Solution solve(ISearchable maze) {
        if(maze == null){
            System.out.println("Can't solve a empty maze!");
            return null;
        }

        AState startState = maze.getStartState();
        AState goalState = maze.getGoalState();
        boolean found = false;
        if (startState == null || goalState == null)
            return null;

        startState.setCost(-1);
        goalState.setCost(0);
        AState currState = startState;
        ArrayList<AState> successors;

        this.openList.add(startState);
        this.visited.put(currState.toString(),true);

        while (!(openList.isEmpty()) && !found){
            currState = this.openList.remove();
            successors = maze.getAllPossibleState(currState);
            this.visited.put(currState.toString(),true);

            if (goalState.equals(currState)){
                found = true;
                break;
            }

            for (AState successor : successors){
                if(!(this.visited.containsKey(successor.toString()))){
                    this.openList.add(successor);
                    this.visited.put(successor.toString(),true);
                }
            }

            this.visitedNodes++;
        }

        return new Solution(currState);
    }
    @Override
    public String getName() {
        return "Breadth First Search";
    }
}

