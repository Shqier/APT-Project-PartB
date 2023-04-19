package algorithms.mazeGenerators;


public class Position {

    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }


    public int getRowIndex(){
        return this.row;
    }

    public int getColumnIndex(){
        return this.col;
    }

    public boolean equals(Position p){
        if (p == null || getClass() != p.getClass())
            return false;

        if (this == p)
            return true;

        if (p.getRowIndex() == this.getRowIndex() && p.getColumnIndex() == this.getColumnIndex())
            return true;

        return false;
    }


    @Override
    public String toString() {
        return "{" + this.row + "," + this.col + "}";
    }

}
