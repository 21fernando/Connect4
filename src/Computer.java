public class Computer {

    private final int COMPUTER = 1; //maximizing
    private final int PLAYER =2; //minimizing

    private int gameWon(int[][] board){
        for(int r=0; r<6; r++){
            for(int c=0; c<7; c++){
                if(board[r][c] != 0){
                    if(c<4){
                        //decreasing diagonal
                        if(r<3){
                            if(board[r][c] == board[r+1][c+1] && board[r][c] == board[r+2][c+2]  && board[r][c] == board[r+3][c+3]){
                                return board[r][c];
                            }
                        }
                        //increasing diagonal
                        if(r>2){
                            if(board[r][c] == board[r-1][c+1] && board[r][c] == board[r-2][c+2] && board[r][c] == board[r-3][c+3]){
                                return board[r][c];
                            }
                        }
                        //horizontal
                        if(board[r][c] == board[r][c+1] && board[r][c] == board[r][c+2] && board[r][c] == board[r][c+3]){
                            return board[r][c];
                        }
                    }
                    //vertical wins
                    if(r<3){
                        if(board[r][c] == board[r+1][c] && board[r][c] == board[r+2][c] && board[r][c] == board[r+3][c]){
                            return board[r][c];
                        }
                    }
                }
            }
        }
        return 0;
    }

    public void computerMove(int[][] board){
        int column = minimax(board, 4,COMPUTER);
        for(int r = 5; r>=0; r--){
            if(board[r][column] == 0){
                System.out.println("Computer playing at row: " + r + " col: " + column);
                board[r][column] = COMPUTER;
                return;
            }
        }
    }

    private boolean columnFull(int[][] board, int column) {
        return board[0][column] != 0;
    }

    private boolean boardIsFull(int[][] board){
        for(int i : board[0]){
            if(i==0) return false;
        }
        return true;
    }

    private void play(int column, int[][] board, int currentPlayer) {
        if(!columnFull(board, column)){
            for(int r = board.length-1; r>=0; r--){
                if(board[r][column] == 0){
                    board[r][column] = currentPlayer;
                    return;
                }
            }
        }
    }

    private void unplay(int[][] board, int column){
        for(int r=0; r<6; r++){
            if(board[r][column] != 0){
                board[r][column] = 0;
                return;
            }
        }
    }

    private int heuristic(int[][]board, int player){
        return 0;
    }


    private int minimax(int[][] board, int depth, int turn){
        if(gameWon(board) == COMPUTER){
            return 50+depth;
        }else if (gameWon(board) == PLAYER){
            return -50-depth;
        }else if(boardIsFull(board)){
            return 0;
        }else if (depth == 0){
            return heuristic(board, turn);
        }else{
            if(turn == PLAYER){
                int minScore = 50;
                int minMove = 0;
                for (int i=0; i<7; i++){
                    if(!columnFull(board, i)){
                        play(i, board, PLAYER);
                        int score = minimax(board, depth-1, COMPUTER);
                        unplay(board, i);
                        if(score<minScore){
                            minScore = score;
                            minMove = i;
                        }
                        System.out.println("PLAYER MOVE: "+ i + " SCORE: " +score);
                    }
                }
                return minMove;
            }else {
                int maxScore = -50;
                int maxMove = 0;
                for (int i=0; i<7; i++){
                    if(!columnFull(board, i)){
                        play(i, board, COMPUTER);
                        int score = minimax(board, depth-1, PLAYER);
                        unplay(board, i);
                        if(score>maxScore){
                            maxScore = score;
                            maxMove = i;
                        }
                        System.out.println("COMPUTER MOVE: "+ i + " SCORE: " +score);
                    }
                }
                return maxMove;
            }
        }
    }

    public static void main(String[] args) {
        Computer app = new Computer();
        int[][] board = new int[][]{
                new int[]{0,0,0,0,0,0,0},
                new int[]{0,0,0,0,0,0,0},
                new int[]{0,0,0,0,0,0,0},
                new int[]{0,2,0,0,0,0,0},
                new int[]{1,2,0,0,0,0,0},
                new int[]{1,2,0,0,0,0,0},
        };
        System.out.println(app.minimax(board, 2, app.COMPUTER));
    }

}
