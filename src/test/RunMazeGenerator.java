package test;
import algorithms.mazeGenerators.*; public class RunMazeGenerator {
    public static void main(String[] args)
    {
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
    // prints the time it takes the algorithm to run System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
    // generate another maze
    Maze maze = mazeGenerator.generate(100/*rows*/, 100/*columns*/);
    } 
}