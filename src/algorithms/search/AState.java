package algorithms.search;
import algorithms.mazeGenerators.Position;



public abstract class AState implements Comparable {

    protected AState cameFrom;
    protected int cost;
    protected String stateName;
    protected int AmountOfPreviousStates;

    public AState(AState cameFrom, String name){
        if (name != null) {
            this.stateName = name;
            this.cameFrom = cameFrom;
        }
        else
            this.stateName = "";

        if(cameFrom != null)
            AmountOfPreviousStates = cameFrom.AmountOfPreviousStates + 1;
        else
            AmountOfPreviousStates = 0;
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int compareTo(Object obj);

    public abstract Position getPosition();

    @Override
    public int hashCode() {
        return this.stateName != null ? this.stateName.hashCode() : 0;
    }

    public AState getCameFrom(){
        return this.cameFrom;
    }

    public int getCost(){
        return this.cost;
    }

    public String getStateName(){
        return this.stateName;
    }

    public void setCameFrom(AState cf){
        if (cf != null)
            this.cameFrom = cf;
    }

    public void setCost(int c){
        this.cost = c;
    }

    @Override
    public String toString() {
        return this.stateName;
    }

}