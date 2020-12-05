

import java.util.Random;
//import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Grid {

    //Class Variables

    private boolean [][] bombGrid;
    private int [][] countGrid;
    private int numRows, numColumns, numBombs, gridCount;
    

    //Class Constructors

    public Grid(){
        numRows = 10;
        numColumns = 10;
        numBombs = 5;
        gridCount = numColumns*numRows;
        createBombGrid();
        createCountGrid();

    }

    public Grid( int rows, int columns){
        numRows = rows;
        numColumns = columns;
        numBombs = 25;
        gridCount = numColumns*numRows;
        createBombGrid();
        createCountGrid();

    }

    public Grid( int rows, int columns, int numBombs){
        numRows = rows;
        numColumns = columns;
        this.numBombs = numBombs;
        gridCount = numColumns*numRows;
        createBombGrid();
        createCountGrid();

    }

    //Getter Methods
    public int getNumRows(){
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public boolean[][] getBombGrid() {
        boolean [][] tempBombGrid = bombGrid;
        return tempBombGrid;
    }

    public int[][] getCountGrid() {
        int [][] tempCountGrid = countGrid;
        return tempCountGrid;
    }

    public int getGridCount() {
        return gridCount;
    }

    public boolean isBombAtLocation(int row, int column){
        
        if (validGridEntry(row, column)){
            return bombGrid[row][column];
        }
        else{
            return false;
        }
        
    }

    public int getCountAtLocation(int row, int column){
        return countGrid[row][column];

    }

    //private Helper Methods;
    private boolean validGridEntry(int row, int column){ //To use with error checking and confirming proper row item entry

        if (row <0 || row >= numRows || column <0 || column >= numColumns){
            return false;
        }
        else{
            return true;
        }
        
        

    }

    private void createBombGrid(){

        bombGrid = new boolean [numRows][numColumns];
        //Randomize bomb generation

        Random rando = new Random();
        

        Set<Integer> set = new HashSet<Integer>(); // inspired by https://stackoverflow.com/a/43261497/14524758 answer on building a quick randomized set of values
        while (set.size() < numBombs){
           set.add(rando.nextInt(gridCount) );
        }
        
        for (Integer location : set){
            bombGrid[(location/numColumns)][(location%numColumns)] = true;
        }
    }

    private void createCountGrid(){
        countGrid = new int [numRows][numColumns];
        for(int row = 0; row<numRows; row ++ ){
            for(int column = 0; column < numColumns; column++){
                countGrid[row][column]=surrondingBombCount(row, column);
            }
        }        
    }


    private int surrondingBombCount(int row, int column){
        int bombCount = 0;

        for(int newRow = row -1; newRow <= row + 1; newRow ++){
            for(int newColumn = column -1; newColumn <= column + 1; newColumn ++){
                if (isBombAtLocation(newRow, newColumn)){
                    bombCount ++;
                }
            }
        }
        return bombCount;
    }
}
