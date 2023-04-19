package algorithms.mazeGenerators;
import java.util.Random;


public class SimpleMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int row, int col) {
        if (row < 2 || col < 2){
            System.out.println("Illegal Maze detected");
            System.out.println("Creating defaut Maze, with the size of (5 * 5)");
            return this.generate(5,5);
        }
        else
        {
            Maze MyMaze = new Maze(row, col);
            int[][] maze = MyMaze.getMaze();
            Random random = new Random();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    MyMaze.setCellValue(i, j, random.nextInt(2));
                }
            }
            int startPoint = random.nextInt(col);
            int goalPoint = random.nextInt(col);
            MyMaze.setCellValue(0, startPoint, 0);
            MyMaze.setStartPosition(0, startPoint);
            MyMaze.setGoalPosition(row - 1, goalPoint);

            this.createSolution(maze, 0, startPoint, goalPoint);

            return MyMaze;
        }

    }

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






