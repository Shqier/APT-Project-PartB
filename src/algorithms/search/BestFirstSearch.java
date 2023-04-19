package algorithms.search;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        super();
        this.openList = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(AState o1, AState o2) {
                if (o1 != null && o2 != null)
                    return o1.getCost() - o2.getCost();
                return -2;
            }
        });
    }
    @Override
    public String getName() {
        return "Best First Search";
    }
}



