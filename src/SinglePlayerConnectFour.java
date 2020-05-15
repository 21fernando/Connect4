import java.util.ArrayList;

public class SinglePlayerConnectFour implements BoardGame{
    private int[][] board; // game board for playing ConnectFour
    private int currentPlayer; // stores the current player's turn
    private Position[] winningPositions; //stores row+colum coordinates when someone wins
    /**
     * Prepares the board for a new game.
     */
    @Override
    public void newGame() {
        int numRows = 6;
        int numCols = 7;
        board = new int[numRows][numCols];
        winningPositions = new Position[4];
        currentPlayer = 1;
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                board[r][c] = 0;
            }
        }
    }

    /**
     * Is the game over?
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean gameOver() {
        if(getWinner() != 0){
            System.out.println("WINNER");
            return true;
        }else{
            for(int c=0; c<7; c++){
                if(!columnFull(c)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Who is the winner?
     * @return 0 if there is no winner, 1 if the first player is a winner, 2 if the second player is a winner.
     */
    @Override
    public int getWinner() {
        for(int r=0; r<6; r++){
            for(int c=0; c<7; c++){
                if(board[r][c] != 0){
                    if(c<4){
                        //decreasing diagonal
                        if(r<3){
                            if(board[r][c] == board[r+1][c+1] && board[r][c] == board[r+2][c+2]  && board[r][c] == board[r+2][c+2]){
                                winningPositions[0] = new Position(r,c);
                                winningPositions[1] = new Position(r+1,c+1);
                                winningPositions[2] = new Position(r+2,c+2);
                                winningPositions[3] = new Position(r+3,c+3);
                                return board[r][c];
                            }
                        }
                        //increasing diagonal
                        if(r>2){
                            if(board[r][c] == board[r-1][c+1] && board[r][c] == board[r-2][c+2] && board[r][c] == board[r-3][c+3]){
                                winningPositions[0] = new Position(r,c);
                                winningPositions[1] = new Position(r-1,c+1);
                                winningPositions[2] = new Position(r-2,c+2);
                                winningPositions[3] = new Position(r-3,c+3);
                                return board[r][c];
                            }
                        }
                        //horizontal
                        if(board[r][c] == board[r][c+1] && board[r][c] == board[r][c+2] && board[r][c] == board[r][c+3]){
                            winningPositions[0] = new Position(r,c);
                            winningPositions[1] = new Position(r,c+1);
                            winningPositions[2] = new Position(r,c+2);
                            winningPositions[3] = new Position(r,c+3);
                            return board[r][c];
                        }
                    }
                    //vertical wins
                    if(r<3){
                        if(board[r][c] == board[r+1][c] && board[r][c] == board[r+2][c] && board[r][c] == board[r+3][c]){
                            winningPositions[0] = new Position(r,c);
                            winningPositions[1] = new Position(r+1,c);
                            winningPositions[2] = new Position(r+2,c);
                            winningPositions[3] = new Position(r+3,c);
                            return board[r][c];
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Where are the tokens that determine who the winner is?
     * @return the locations of the pieces that determine the game winner.
     */
    @Override
    public Position[] getWinningPositions() {
        return winningPositions;
    }

    /**
     * Does the column have room for an additional move?
     * @param column the column number
     * @return false if there is room for another move in the column, true if not.
     */
    @Override
    public boolean columnFull(int column) {
        return board[0][column] != 0;
    }

    /**
     * Change the game to reflect the current player placing a piece in the columnn
     * @param column the column number
     */
    @Override
    public void play(int column) {
        for(int r = 5; r>=0; r--){
            if(board[r][column] == 0){
                System.out.println(currentPlayer + " playing at row: " + r + " col: " + column);
                board[r][column] = currentPlayer;
                currentPlayer = (currentPlayer ==1)? 2: 1;
                return;
            }
        }
    }

    /**
     * What is the current board configuration?
     * @return for each cell on the board grid:
     * 0 if it is not filled,
     * 1 if it is filled by the first player's piece,
     * 2 if it is filled by the second player's piece.
     */
    @Override
    public int[][] getBoard() {
        return board;
    }

    public void computerMove(){
        play(minimax(board, 7,5));
    }

    private int minimax(int[][] board, int depth, int player){
        ArrayList<Integer> choices = new ArrayList<>();
        for(int c =0 ; c< 7; c++){
            if(!columnFull(c)) choices.add(c);
        }
        return choices.get((int)(Math.random()*choices.size()));
    }
}
