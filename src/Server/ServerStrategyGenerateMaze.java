package Server;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;
import java.io.*;


public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            Maze new_maze;
            byte[] byteMaze;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            IMazeGenerator generator = Configurations.getGenerating();
            int[] mazeSize = (int[])fromClient.readObject();




            if (mazeSize instanceof int[] && mazeSize.length == 2)
                new_maze = generator.generate(mazeSize[0], mazeSize[1]);

            else // Client Sent us Wrong Format - Default Maze Size
                new_maze = (generator.generate(10, 10));

            byteMaze = new_maze.toByteArray();
            ByteArrayOutputStream toClientBytes = new ByteArrayOutputStream();
            MyCompressorOutputStream compressedToClient = new MyCompressorOutputStream(toClientBytes);
            compressedToClient.write(byteMaze);
            toClient.writeObject(toClientBytes.toByteArray());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
