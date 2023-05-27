package algorithms.mazeGenerators;


import java.io.Serializable;

public class Maze implements Serializable {

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

    public Maze(byte[] byteArray){
        this.row = (int)byteArray[0] * 127 + (int)byteArray[1];
        this.col = (int)byteArray[2] * 127 + (int)byteArray[3];
        this.startPosition = new Position((int)byteArray[4] * 127 + (int)byteArray[5], (int)byteArray[6] * 127 + (int)byteArray[7]);
        this.goalPosition = new Position((int)byteArray[8] * 127 + (int)byteArray[9], (int)byteArray[10] * 127 + (int)byteArray[11]);
        this.maze = new int[row][col];
        int index = 12;
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                maze[i][j] = byteArray[index];
                index++;
            }
        }
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


    public byte[] toByteArray(){
        int num = 127;
        byte[] byteArray = new byte[row * col + 12];

        byte rowByteInt = (byte) (row / num);
        byte rowByteRest = (byte) (row % num);

        byte colByteInt = (byte) (col / num);
        byte colByteRest = (byte) (col % num);

        byte startPosRowByteInt = (byte) (this.getStartPosition().getRowIndex() / num);
        byte startPosRowByteRest = (byte) (this.getStartPosition().getRowIndex() % num);

        byte startPosColByteInt = (byte) (this.getStartPosition().getColumnIndex() / num);
        byte startPosColByteRest = (byte) (this.getStartPosition().getColumnIndex() % num);

        byte goalPosRowByteInt = (byte) (this.getGoalPosition().getRowIndex() / num);
        byte goalPosRowByteRest = (byte) (this.getGoalPosition().getRowIndex() % num);

        byte goalPosColByteInt = (byte) (this.getGoalPosition().getColumnIndex() / num);
        byte goalPosColByteRest = (byte) (this.getGoalPosition().getColumnIndex() % num);

        byteArray[0] = rowByteInt;
        byteArray[1] = rowByteRest;

        byteArray[2] = colByteInt;
        byteArray[3] = colByteRest;

        byteArray[4] = startPosRowByteInt;
        byteArray[5] = startPosRowByteRest;

        byteArray[6] = startPosColByteInt;
        byteArray[7] = startPosColByteRest;

        byteArray[8] = goalPosRowByteInt;
        byteArray[9] = goalPosRowByteRest;

        byteArray[10] = goalPosColByteInt;
        byteArray[11] = goalPosColByteRest;

        int index = 12;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                byteArray[index] = (byte) (maze[i][j]);
                index++;
            }
        }

        return byteArray;
    }


}
