package Server;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import java.io.*;
import java.util.Properties;


public class Configurations{

    private static Properties prop = new Properties();
    private static OutputStream out = null;
    private static InputStream incon;
    private static Configurations con = null;
    public static Properties getProp(){return prop;}


    private Configurations(){
        try {
            incon = getClass().getClassLoader().getResourceAsStream("config.properties");
            if(incon!=null) {
                prop.load(incon);
            }
            else {
                throw  new FileNotFoundException();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configurations getconfig() {
        if(con==null){
            con=new Configurations();
        }
        return con;
    }


    public static int GetNumOfThreads(){
        return Integer.parseInt(prop.getProperty("NumberOfThreads"));
    }
    public static ISearchingAlgorithm getSearchingAlg(){
        if(prop.getProperty("Searching").equals("DepthFirstSearch")){
            return new DepthFirstSearch();
        }
        else if (prop.getProperty("Searching").equals("BestFirstSearch")) {
            return new BestFirstSearch();
        }
        else {
            return new BreadthFirstSearch();
        }
    }


    public static IMazeGenerator getGenerating(){
        if(prop.getProperty("MazeGenerator").equals("MyMazeGenerator"))
        {
            return new MyMazeGenerator();

        }
        else if (prop.getProperty("MazeGenerator").equals("SimpleMazeGenerator")) {
            return new SimpleMazeGenerator();
        }
        else {
            return new EmptyMazeGenerator();
        }
    }

}
