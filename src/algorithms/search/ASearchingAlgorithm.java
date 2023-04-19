package algorithms.search;



public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected int visitedNodes;
    protected String name;



    public ASearchingAlgorithm(){
        this.visitedNodes = 0;
        this.name = null;
    }


    @Override
    public abstract Solution solve(ISearchable maze);


    @Override
    public int getNumberOfNodesEvaluated() {
        return this.visitedNodes;
    }



    @Override
    public String getName() {
        return this.name;
    }

}
