
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridGUI extends JFrame implements ActionListener {

   
    JPanel minePanel = null;
    JButton newGame;
    JButton closeGame;
    JButton [] replayOptions = {newGame, closeGame};
    JButton[][] grids;
    GridBagConstraints layout = null;
    Container board = new Container();
    Grid newMineGame = null;
    int remainingGrids;

    public GridGUI() {// static void mineSweepGrid(){
        // GUI Component Variables

        newMineGame = new Grid();
        remainingGrids = newMineGame.getGridCount() - newMineGame.getNumBombs();

        newGame = new JButton("New Game");
        closeGame = new JButton("Close Game");
        board.setLayout(new GridLayout(newMineGame.getNumColumns(),newMineGame.getNumRows()));
        grids = new JButton[newMineGame.getNumRows()][newMineGame.getNumColumns()];
        for (int rows = 0; rows < newMineGame.getNumRows(); rows++) {
            for (int columns = 0; columns < newMineGame.getNumColumns(); columns++) {
                grids [rows][columns] = new JButton();
                grids [rows][columns].addActionListener(this);
                grids [rows][columns].setForeground(Color.black);
                board.add(grids [rows][columns]);
            }
        }
        // Create Application Frame Title& layout
        setTitle("MineSweeper");
        setSize(500, 500);
        setLayout(new GridBagLayout());

        layout = new GridBagConstraints();
        layout.insets = new Insets(10, 10, 10, 10);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        add(board, layout);
       

    }

    @Override
    public void actionPerformed(ActionEvent event) {



        if (event.getSource().equals(newGame) ){
            new GridGUI();
        }

        else {
            for (int rows = 0; rows < newMineGame.getNumRows(); rows++) {
                for (int columns = 0; columns < newMineGame.getNumColumns(); columns++) {
                    if (event.getSource().equals(grids[rows][columns]) ) {
                        if (newMineGame.isBombAtLocation(rows, columns)){
                            grids[rows][columns].setForeground(Color.RED);
                            grids[rows][columns].setText("Mine");
                            mineSweepGameLoss();
                        }
                        else{
                            grids[rows][columns].setEnabled(false);
                            remainingGrids --;
                            grids[rows][columns].setForeground(Color.WHITE);
                            grids[rows][columns].setText(newMineGame.getCountGrid()[rows][columns] + "");
                            if(newMineGame.getCountGrid()[rows][columns] == 0) ifSquareIsZero(rows, columns) ;
                            if(remainingGrids == 0 ) {
                                mineSweepWinCheck();
                            }
                        }  
                    }
                }
            }
        }
    }


    private  void mineSweepGameLoss() {
        // JOptionPane.showMessageDialog(this, "You clicked a mine!", "Kaboom!", JOptionPane.INFORMATION_MESSAGE);
        for (int rows = 0; rows < newMineGame.getNumRows(); rows++) {
            for (int columns = 0; columns < newMineGame.getNumColumns(); columns++) {
                if (newMineGame.isBombAtLocation(rows, columns)){
                    grids[rows][columns].setForeground(Color.RED);
                    grids[rows][columns].setText("Mine");
                    grids[rows][columns].setEnabled(false);
                }
                else{
                    grids[rows][columns].setEnabled(false);
                    grids[rows][columns].setForeground(Color.WHITE);
                    grids[rows][columns].setText(newMineGame.getCountGrid()[rows][columns] + "");
                }
            }
        }
        int yesNo = JOptionPane.showConfirmDialog(this, "Play Again?", "You Clicked a Mine! KABOOM!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (yesNo == JOptionPane.YES_OPTION){
            GridGUI reMatch = new GridGUI();
            reMatch.setDefaultCloseOperation(reMatch.EXIT_ON_CLOSE);
        
            reMatch.setVisible(true);
        }
        else{
            System.exit(0);
        }    }

    private void ifSquareIsZero(int rows, int columns) {
        for(int newRow = rows -1; newRow <= rows + 1; newRow ++){
            for(int newColumn = columns -1; newColumn <= columns + 1; newColumn ++){
                if (newRow <0 || newRow >= newMineGame.getNumRows() || newColumn <0 || newColumn >= newMineGame.getNumColumns() || !(grids[newRow][newColumn].isEnabled() ) ){
                    continue;
                }
                grids[newRow][newColumn].setForeground(Color.WHITE);
                grids[newRow][newColumn].setText(newMineGame.getCountGrid()[newRow][newColumn] + "");
                grids[newRow][newColumn].setEnabled(false);
                remainingGrids --;
                if(newMineGame.getCountGrid()[newRow][newColumn] == 0) {
                    ifSquareIsZero(newRow, newColumn);
                }
                else {
                    continue;
                }
            }
        }
    }
     
    private void mineSweepWinCheck() {
        for (int rows = 0; rows < newMineGame.getNumRows(); rows++) {
            for (int columns = 0; columns < newMineGame.getNumColumns(); columns++) {
                if (newMineGame.isBombAtLocation(rows, columns)){
                    grids[rows][columns].setForeground(Color.RED);
                    grids[rows][columns].setText("Mine");
                    grids[rows][columns].setEnabled(false);
                }
                else{
                    grids[rows][columns].setEnabled(false);
                    grids[rows][columns].setForeground(Color.WHITE);
                    grids[rows][columns].setText(newMineGame.getCountGrid()[rows][columns] + "");
                }
            }
        }
        int yesNo = JOptionPane.showConfirmDialog(this, "Play Again?", "You've Cleared All the Mines! Congrats!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (yesNo == JOptionPane.YES_OPTION){
            GridGUI reMatch = new GridGUI();
            reMatch.setDefaultCloseOperation(reMatch.EXIT_ON_CLOSE);
            reMatch.pack();
            reMatch.setVisible(true);
        }
        else{
            System.exit(0);
        }
        
        // JOptionPane.showMessageDialog(this, "Play Again?", "You've Cleared All the Mines! Congrats!", JOptionPane.INFORMATION_MESSAGE);


    }
}
