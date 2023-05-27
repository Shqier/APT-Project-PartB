package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;


public class MazeState extends AState implements Serializable{

    private Position position;

    public MazeState(AState cameFrom, String name, Position p){
        super(cameFrom, name);
        if (p != null)
            this.position = new Position(p.getRowIndex(), p.getColumnIndex());
        else
            this.position = null;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof MazeState)
            if (this.getRowIndex() == ((MazeState) obj).getRowIndex() && this.getColIndex() == ((MazeState) obj).getColIndex())
                return 0;

        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MazeState)
            return (this.getRowIndex() == ((MazeState) obj).getRowIndex() && this.getColIndex() == ((MazeState) obj).getColIndex());

        return false;
    }


    public int getRowIndex(){
        if (this.position == null)
            return -1;

        return this.position.getRowIndex();
    }
   

    public Position getPosition(){
        return this.position;
    }

    public int getColIndex(){
        if (this.position == null)
            return -1;
        return this.position.getColumnIndex();
    }

}
