package algorithms.mazeGenerators;



public class Maze {

    private int row;
    private int col;
    private Position startPosition;
    private Position goalPosition;
    private int[][] maze;


    public Maze(int row, int col) {
        this.row = row;
        this.col = col;
        this.startPosition = new Position(0,0);
        this.goalPosition = new Position(row - 1, col - 1);
        maze = new int[row][col];
        maze[0][0] = 0;
    }



    public Position getStartPosition(){
        return this.startPosition;
    }

    public Position getGoalPosition() {
        return this.goalPosition;
    }


    public int getRowIndex(){
        return this.row;
    }

    public int getColumnIndex(){
        return this.col;
    }


    public int[][] getMaze(){
        return this.maze;
    }

    public int getCellValue(int row, int col){
        if (row < 0 || col < 0 || row > this.row - 1 || col > this.col - 1 || this.maze == null)
            return -1;
        return this.maze[row][col];
    }


    public void setStartPosition(int row, int col){
        if (row < this.row && col < this.col)
            this.startPosition = new Position(row, col);
        else
            System.out.println("coordinates out of bounds of the maze's size!");
    }


    public void setGoalPosition(int row, int col){
        if (row < this.row && col < this.col)
            this.goalPosition = new Position(row, col);
        else
            System.out.println("coordinates out of bounds of the maze's size!");
    }


    public void setCellValue(int row, int col, int val){
        this.maze[row][col] = val;
    }

    public void print(){
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex())
                    System.out.print(" \033[0;31m" + "S" + "\033[0m");

                else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex())
                    System.out.print(" \033[0;32m" + "E" + "\033[0m");

                else
                    System.out.print(" " + this.maze[i][j]);

                if (j == this.col - 1)
                    System.out.println();
            }
        }
    }

    public void setmat(int[][] mat){
        this.maze = mat;
    }

}
