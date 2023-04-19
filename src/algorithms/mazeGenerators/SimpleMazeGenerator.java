package src.algorithms.mazeGenerators;
import java.util.Random;


/**
 * SimpleMazeGenerator class that extends AMazeGenerator abstract class.
 * responsible for generating a simple random maze.
 */

public class SimpleMazeGenerator extends AMazeGenerator{

    /**
     * method for generating simple maze, with the use of randomised values in the cell with '1' or '0'
     * @param row: number of rows in the maze.
     * @param col: number of columns in the maze.
     * @return simple random maze with given size of row and column.
     */
    @Override
    public Maze generate(int row, int col) {
        if (row < 2 || col < 2){
            System.out.println("Illegal Maze detected");
            System.out.println("Creating defaut Maze, with the size of (5 * 5)");
            return this.generate(5,5);
        }
        else
        {
            Maze newMaze = new Maze(row, col);
            int[][] maze = newMaze.getMaze();
            Random random = new Random();
            // Fill the maze with random values
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    newMaze.changeValue(i, j, random.nextInt(2));
                }
            }

            // Set the start and goal positions
            int startPoint = random.nextInt(col);
            int goalPoint = random.nextInt(col);
            maze[0][startPoint] = 0;
            newMaze.setStartPosition(0, startPoint);
            newMaze.setGoalPosition(row - 1, goalPoint);

            // Create the solution for the maze
            this.createSolution(maze, 0, startPoint, goalPoint);

            return newMaze;
        }

    }

    /**
     * this method is responsible for making a path for the maze by marking it with '0'
     * @param maze: 2D array maze board.
     * @param row: row index.
     * @param col: column index.
     * @param finalPoint: the goal position of the maze.
     */
    // public void createSolution(int[][] maze, int row, int col, int finalPoint){
    //     Random rand = new Random();
    //     maze[maze.length - 1][finalPoint] = 1;
    //     while (maze[maze.length - 1][finalPoint] == 1){
    //         switch (rand.nextInt(3)){
    //             case 0://down
    //                 if (row + 1 < maze.length){
    //                     maze[row + 1][col] = 0;
    //                     row++;
    //                 }
    //                 break;

    //             case 1://right
    //                 if (col + 1 < maze[row].length){
    //                     maze[row][col + 1] = 0;
    //                     col++;
    //                 }
    //                 break;

    //             case 2://left
    //                 if (col - 1 >= 0){
    //                     maze[row][col - 1] = 0;
    //                     col--;
    //                 }
    //                 break;
    //         }
    //     }
    // }
    public void createSolution(int[][] maze, int row, int col, int finalPoint) {
        Random rand = new Random();
        maze[maze.length - 1][finalPoint] = 1;
        while (maze[maze.length - 1][finalPoint] == 1) 
        {
            int direction = rand.nextInt(3);
            if (direction == 0 && row + 1 < maze.length)
            { 
                // down
                maze[++row][col] = 0; 
            } 
            else if (direction == 1 && col + 1 < maze[row].length)
            { 
                // right 
                maze[row][++col] = 0; 
            } 
            else if (direction == 2 && col - 1 >= 0) 
            { 
                // left
                maze[row][--col] = 0;
            }
        }
    }

}






