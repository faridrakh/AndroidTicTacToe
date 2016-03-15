package com.example.farid.androidtictactoe;

/**
 * Created by Farid on 14/03/2016.
 */
public class TicTacToeBoard {
    public int[] board;
    public static final int EMPTY = 0;
    public static final int PLAYER = 1;
    public static final int COMPUTER = 2;
    public static final String[] SYMBOL = {"","X","O"};

    public TicTacToeBoard(){
        board = new int[9];
    }

    public int bestMove(int turn){
        for(int i = 0; i < 9; i++){
            if(board[i] == EMPTY){
                board[i] = turn;
                boolean win = checkWin(turn);
                board[i] = EMPTY;
                if (win) return i;
            }
        }
        return -1;
    }

    public int nextMove(int turn){
        //get center
        if(board[4] == EMPTY) return 4;

        //best move current player
        int winMove = bestMove(turn);
        if(winMove != -1) return winMove;

        //best move for defend
        for(int i = 0; i < 9; i++) {
            if (board[i] == EMPTY) {
                board[i] = turn;
                boolean win = bestMove(turn == PLAYER ? COMPUTER : PLAYER) == -1;
                board[i] = EMPTY;
                if (win) return i;
            }
        }

        //get available move
        for(int i = 0; i < 9; i++){
            if(board[i] == EMPTY) return i;
        }
        //no more move
        return -1;
    }
    public boolean checkWin(int turn){
        if(board[0] == turn && board[1] == turn && board[2] == turn) return true;
        if(board[0] == turn && board[3] == turn && board[6] == turn) return true;
        if(board[0] == turn && board[4] == turn && board[8] == turn) return true;
        if(board[1] == turn && board[4] == turn && board[7] == turn) return true;
        if(board[2] == turn && board[5] == turn && board[8] == turn) return true;
        if(board[2] == turn && board[4] == turn && board[6] == turn) return true;
        if(board[3] == turn && board[4] == turn && board[5] == turn) return true;
        if(board[6] == turn && board[7] == turn && board[8] == turn) return true;
        return false;
    }

}
