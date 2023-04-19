package algorithms.mazeGenerators;


public class EmptyMazeGenerator extends AMazeGenerator{

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
