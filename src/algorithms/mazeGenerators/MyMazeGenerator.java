package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Random;



public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int row, int col) {
        if (row >= 2 && col >= 2){

            Maze MyMaze = new Maze(row, col);
            Random rand = new Random();
            ArrayList<Position> wallsList = new ArrayList<>();
            Position startPoint = new Position(0, rand.nextInt(col));
            Position goalPoint = new Position(row - 1, rand.nextInt(col));
            MyMaze.setStartPosition(startPoint.getRowIndex(), startPoint.getColumnIndex());

            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    MyMaze.setCellValue(i, j, 1);

            MyMaze.setCellValue(startPoint.getRowIndex(), startPoint.getColumnIndex(), 0);
            this.addWalls(MyMaze, wallsList, startPoint.getRowIndex(), startPoint.getColumnIndex());

            while (!wallsList.isEmpty()){
                Position randomWall = wallsList.get(rand.nextInt(wallsList.size()));
                ArrayList<Position> neighbors = FindNeighbors(MyMaze, randomWall.getRowIndex(), randomWall.getColumnIndex());
                if (neighbors.size() == 1){
                    MyMaze.setCellValue(randomWall.getRowIndex(), randomWall.getColumnIndex(), 0);
                    this.addWalls(MyMaze, wallsList, randomWall.getRowIndex(), randomWall.getColumnIndex());
                }

                wallsList.remove(randomWall);
            }

            MyMaze.setGoalPosition(goalPoint.getRowIndex(), goalPoint.getColumnIndex());
            MyMaze.setCellValue(goalPoint.getRowIndex(), goalPoint.getColumnIndex(), 0);

            return MyMaze;
        }

        else{
            System.out.println("Illegal Maze detected");
            System.out.println("Creating defaut Maze, with the size of (5 * 5)");
            return this.generate(5,5);
        }

    }


 
    private void addWalls(Maze mymaze, ArrayList<Position> wallsList, int row, int col){
        int[][] maze = mymaze.getMaze();
        if (col + 1 < maze[row].length){ //right
            if (maze[row][col + 1] == 1)
                wallsList.add(new Position(row, col + 1));
        }

        if(col - 1 >= 0){ //left
            if(maze[row][col - 1] == 1)
                wallsList.add(new Position(row, col - 1));
        }

        if(row - 1 >= 0){ //up
            if(maze[row - 1][col] == 1)
                wallsList.add(new Position(row - 1, col));
        }

        if(row + 1 < maze.length){ //down
            if(maze[row + 1][col] == 1)
                wallsList.add(new Position(row + 1, col));
        }

    }



    private ArrayList<Position> FindNeighbors(Maze mymaze, int row, int column) {
        int[][] maze = mymaze.getMaze();
        ArrayList<Position> neighbors = new ArrayList<>();
        if(column + 1 < maze[row].length){ //right
            if(maze[row][column + 1] == 0)
                neighbors.add(new Position(row, column + 1));
        }

        if(column - 1 >= 0){ //left
            if(maze[row][column - 1] == 0)
                neighbors.add(new Position(row, column - 1));
        }

        if(row - 1 >= 0 ){ //up
            if(maze[row - 1][column] == 0)
                neighbors.add(new Position(row - 1, column ));
        }

        if(row + 1 < maze.length){ //down
            if(maze[row + 1][column] == 0)
                neighbors.add(new Position(row + 1, column));
        }

        return neighbors;
    }

}
