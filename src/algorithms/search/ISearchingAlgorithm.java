package algorithms.search;


public interface ISearchingAlgorithm {

    Solution solve(ISearchable search);

    int getNumberOfNodesEvaluated();

    String getName();

}
