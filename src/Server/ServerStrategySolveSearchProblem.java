package Server;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.io.*;


public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private int counter=0;
    private boolean flag =false;


    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            ObjectOutputStream out = new ObjectOutputStream(outToClient);
            ObjectInputStream in = new ObjectInputStream(inFromClient);
            out.flush();

            try {
                Maze mazes = (Maze) in.readObject();
                byte[] byteMaze = mazes.toByteArray();
                File file = new File(System.getProperty("java.io.tmpdir"));
                File[] tempF = file.listFiles();
                if(tempF!=null){
                    for(File it : tempF){
                        if(!(it.getName().contains("SolutionNumber"))){
                            break;
                        }
                        ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(it));
                        if(byteMaze.equals( fromFile.readObject())){
                            flag =true;
                            try {
                                InputStream solutionFile = new FileInputStream(System.getProperty("user.dir")+"\\"+it.getName());
                                ObjectInputStream solution = new ObjectInputStream(solutionFile);
                                out.writeObject(solution.readObject());
                                solutionFile.close();
                                fromFile.close();
                                solution.close();
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }
                if(!flag){
                    ASearchingAlgorithm searchAlgo;
                    SearchableMaze maze1 = new SearchableMaze(mazes);
                    String prop = Configurations.getProp().getProperty("Searching");
                    if(prop.equals("BreadthFirstSearch")){
                        searchAlgo = new BreadthFirstSearch();
                    } else if (prop.equals("DepthFirstSearch")) {
                        searchAlgo = new DepthFirstSearch();
                    }
                    else {
                        searchAlgo = new BestFirstSearch();
                    }
                    Solution sol = searchAlgo.solve(maze1);

                    out.writeObject(sol);

                    ObjectOutputStream mazeF = new ObjectOutputStream(new FileOutputStream(System.getProperty("java.io.tmpdir")+"\\"+ "sol" + counter));
                    mazeF.writeObject(byteMaze);
                    ObjectOutputStream mazeF1 = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\"+ "sol" + counter));
                    mazeF1.writeObject(sol);
                    counter++;
                }

            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
