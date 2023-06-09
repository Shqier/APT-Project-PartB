package algorithms.search;
import java.util.ArrayList;


public interface ISearchable {

    AState getStartState();

    AState getGoalState();

    ArrayList<AState> getAllPossibleState(AState state);

}
