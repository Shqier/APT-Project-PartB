package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Random;


/**
 * MyMazeGenerator class that extends AMazeGenerator abstract class.
 * this class responsible for generating a maze based on Randomized Prim's Algorithm
 */

public class MyMazeGenerator extends AMazeGenerator{

    /**
     * method for generating the maze, The cells value of the maze ('path and walls) chosen randomly and based on Prim's algorithm.
     * but the maze always has solution.
     * @param row: number of rows in the maze.
     * @param col: number of columns in the maze.
     * @return maze generated with Prim's algorithm, with given size of row and column.
     */
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


            // for (int i = 0; i < row; i++)
            // for (int j = 0; j < col; j++)
            //     MyMaze.setCellValue(i, j, 1);

            // MyMaze.setCellValue(startPoint.getRowIndex(), startPoint.getColumnIndex(), 0);

            // // Initialize all walls as edges in the graph
            // for (int i = 0; i < row; i++) {
            //     for (int j = 0; j < col; j++) {
            //         if (i > 0) wallsList.add(new Position(i, j, i - 1, j));
            //         if (j > 0) wallsList.add(new Position(i, j, i, j - 1));
            //     }
            // }

            // // Randomly shuffle the walls list
            // Collections.shuffle(wallsList, rand);

            // // Iterate through the walls list and add edges to the graph
            // for (Position wall : wallsList) {
            //     int row1 = wall.getRowIndex();
            //     int col1 = wall.getColumnIndex();
            //     int row2 = wall.getRowIndex();
            //     int col2 = wall.getColumnIndex();

            //     // Check if the two cells are in different sets
            //     if (MyMaze.getCellValue(row1, col1) != MyMaze.getCellValue(row2, col2)) {
            //         MyMaze.setCellValue(row1, col1, 0);
            //         MyMaze.setCellValue(row2, col2, 0);

            //         // Merge the two sets
            //         int set1 = MyMaze.getCellValue(row1, col1);
            //         int set2 = MyMaze.getCellValue(row2, col2);
            //         for (int i = 0; i < row; i++) {
            //             for (int j = 0; j < col; j++) {
            //                 if (MyMaze.getCellValue(i, j) == set2) {
            //                     MyMaze.setCellValue(i, j, set1);
            //                 }
            //             }
            //         }
            //     }
            // }

            // MyMaze.setGoalPosition(goalPoint.getRowIndex(), goalPoint.getColumnIndex());
            // MyMaze.setCellValue(goalPoint.getRowIndex(), goalPoint.getColumnIndex(), 0);

            // return MyMaze;

        }

        else{
            System.out.println("Illegal Maze detected");
            System.out.println("Creating defaut Maze, with the size of (5 * 5)");
            return this.generate(5,5);
        }

    }


    /**
     * private method for adding walls that are in the maze to a walls list.
     * this method gets walls list ,maze and Position [row,col]
     * and add to the walls list all the walls the beside to the given position.
     * @param maze: 2D array of the maze.
     * @param wallsList: the walls list to add to.
     * @param row: row index.
     * @param col: col index.
     */
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


    /**
     * private method that adds all the visited neighbors of a specific position to an array.
     * this method gets the maze and a specific position [row,col]
     * and return the number of visited neighbors of the given position.
     * @param maze: 2D array of the maze.
     * @param row: row index.
     * @param column: col index.
     * @return
     */
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
