package algorithms.search;
import java.util.ArrayList;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;


public class SearchableMaze implements ISearchable {

    private Maze Maze;
    private MazeState startState;
    private MazeState goalState;
    private boolean[][] visited;

    public SearchableMaze(Maze Maze) {
        if (Maze != null) {
            this.Maze = Maze;
            String Start = Maze.getStartPosition().toString();
            String End = Maze.getGoalPosition().toString();
            this.startState = new MazeState(null, Start, Maze.getStartPosition());
            this.goalState = new MazeState(null, End, Maze.getGoalPosition());
            this.visited = new boolean[Maze.getRowIndex()][Maze.getColumnIndex()];

            for (int i = 0; i < Maze.getRowIndex(); i++){
                for(int j = 0; j < Maze.getColumnIndex(); j++){
                    if(Maze.getCellValue(i, j) == 0)
                        this.visited[i][j] = false;
                    else
                        this.visited[i][j] = true;
                }
            }
        }

        else{
            this.Maze = null;
            this.startState = null;
            this.goalState = null;
            System.out.println("Can't construct a null Maze!");
        }
    }

    @Override
    public AState getStartState() {
        return this.startState;
    }

    @Override
    public AState getGoalState() {
        return this.goalState;
    }

    @Override
    public ArrayList<AState> getAllPossibleState(AState state) {
        if (state.cost == -1) {

            state.setCost(0);
            for (int i =0 ; i < this.Maze.getRowIndex(); i++) {
                for (int j = 0 ; j < this.Maze.getColumnIndex(); j++) {
                    if(this.Maze.getCellValue(i, j) == 0)
                        this.visited[i][j] = false;
                    else
                        this.visited[i][j] = true;
                }
            }
        }


        ArrayList<AState> possibleStates = new ArrayList<>();
        Position position;
        MazeState currState;
        if (state != null) {
            MazeState mazestate = (MazeState) state;
            int stateRow = mazestate.getRowIndex();
            int stateCol = mazestate.getColIndex();
            boolean up, right, down, left;
            boolean rightUp, rightDown, leftDown, leftUp;


            if (!this.visited[stateRow][stateCol]) {

                this.visited[stateRow][stateCol] = true;
                up = isValid(mazestate, stateRow - 1, stateCol);
                right = isValid(mazestate, stateRow, stateCol + 1);
                down = isValid(mazestate, stateRow + 1, stateCol);
                left = isValid(mazestate, stateRow, stateCol - 1);

                rightUp = isValid(mazestate, stateRow - 1, stateCol + 1);
                rightDown = isValid(mazestate, stateRow + 1, stateCol + 1);
                leftDown = isValid(mazestate, stateRow + 1, stateCol - 1);
                leftUp = isValid(mazestate, stateRow - 1, stateCol - 1);

                if (up && !this.visited[stateRow - 1][stateCol]) {
                    position = new Position(stateRow - 1, stateCol);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 10);
                    possibleStates.add(currState);
                }

                if (right && !this.visited[stateRow][stateCol + 1]) {
                    position = new Position(stateRow, stateCol + 1);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 10);
                    possibleStates.add(currState);
                }

                if (down && !this.visited[stateRow + 1][stateCol]) {
                    position = new Position(stateRow + 1, stateCol);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 10);
                    possibleStates.add(currState);
                }

                if (left && !this.visited[stateRow][stateCol - 1]) {
                    position = new Position(stateRow, stateCol - 1);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 10);
                    possibleStates.add(currState);
                }


                if (rightUp && (right || up) && !this.visited[stateRow - 1][stateCol + 1]) {
                    position = new Position(stateRow - 1, stateCol + 1);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 15);
                    possibleStates.add(currState);
                }

                if (rightDown && (right || down) && !this.visited[stateRow + 1][stateCol + 1]) {
                    position = new Position(stateRow + 1, stateCol + 1);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 15);
                    possibleStates.add(currState);
                }

                if (leftDown && (left || down) && !this.visited[stateRow + 1][stateCol - 1]) {
                    position = new Position(stateRow + 1, stateCol - 1);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 15);
                    possibleStates.add(currState);
                }

                if (leftUp && (left || up) && !this.visited[stateRow - 1][stateCol - 1]) {
                    position = new Position(stateRow - 1, stateCol - 1);
                    currState = new MazeState(state, position.toString(), position);
                    currState.setCost(state.getCost() + 15);
                    possibleStates.add(currState);
                }

            }
        }

        return possibleStates;
    }


    /**
     * private method for checking if a state in [row,col] is a valid successor to the given state.
     * @param state: state that we want to check its valid successors.
     * @param row: row of the successor.
     * @param col: col of the successor.
     * @return a boolean value if this successor is valid or not.
     */
    private boolean isValid(MazeState state, int row, int col){
        int rowSize = this.Maze.getRowIndex();
        int colSize = this.Maze.getColumnIndex();
        MazeState cameFrom = (MazeState) state.getCameFrom();
        int cameFromRow = -1;
        int cameFromCol = -1;
        int cellValue;

        if (cameFrom != null){
            cameFromRow = cameFrom.getRowIndex();
            cameFromCol = cameFrom.getColIndex();
        }

        if (cameFromRow == row && cameFromCol == col)
            return false;

        else if (row < rowSize && col < colSize && row >= 0 && col >= 0){
            cellValue = this.Maze.getCellValue(row, col);
            if (cellValue == 0)
                return true;
        }

        return false;
    }

}
