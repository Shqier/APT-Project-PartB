package algorithms.mazeGenerators;


/**
 * EmptyMazeGenerator class that extends AMazeGenerator abstract class.
 * responsible for generating an empty maze, which means maze without walls.
 */

public class EmptyMazeGenerator extends AMazeGenerator{

    /**
     * method for generating an empty maze.
     * @param row: number of rows in the maze.
     * @param col: number of columns in the maze.
     * @return empty maze with given size of row and column without walls.
     */
    @Override
    public Maze generate(int row, int col) {
        if (row >= 2 && col >= 2) {

            Maze emptyMaze = new Maze(row, col);
            return emptyMaze;
        }

        else{
            System.out.println("Illegal Maze detected");
            System.out.println("Creating defaut Maze, with the size of (5 * 5)");
            return this.generate(5,5);
        }

    }

}
