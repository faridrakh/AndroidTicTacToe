package com.example.farid.androidtictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int loc;
    TicTacToeBoard board;
    private boolean isPlay = true;
    int[] cellid = {R.id.cell11, R.id.cell12, R.id.cell13, R.id.cell21, R.id.cell22, R.id.cell23, R.id.cell31, R.id.cell32, R.id.cell33};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        board = new TicTacToeBoard();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 9; i++){
                    board.board[i] = 0;
                }
                isPlay = true;
                TextView cell;
                for (int item : cellid) {
                    cell = (TextView) findViewById(item);
                    cell.setText("");
                }
            }
        });
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void cellClick(View v) {
        TextView cell = (TextView) findViewById(v.getId());
        String content = (String) cell.getText();
        if (content == "" && isPlay == true) {
            //Find the board location values of the particular cell that was clicked
            switch (cell.getId()) {
                case R.id.cell11:
                    loc = 0;
                    break;
                case R.id.cell12:
                    loc = 1;
                    break;
                case R.id.cell13:
                    loc = 2;
                    break;
                case R.id.cell21:
                    loc = 3;
                    break;
                case R.id.cell22:
                    loc = 4;
                    break;
                case R.id.cell23:
                    loc = 5;
                    break;
                case R.id.cell31:
                    loc = 6;
                    break;
                case R.id.cell32:
                    loc = 7;
                    break;
                case R.id.cell33:
                    loc = 8;
                    break;
            }
            cell.setText(board.SYMBOL[board.PLAYER]);
            board.board[loc] = board.PLAYER;
            checkWhoWin();
            if(isPlay == true) {
                computerMove();
            }
        }
    }

    public void computerMove(){
        int x = board.nextMove(board.COMPUTER);
        Log.i("X",""+ x);
        TextView cell2 = null;
        switch (x) {
            case 0:
                cell2 = (TextView) findViewById(R.id.cell11);
                break;
            case 1:
                cell2 = (TextView) findViewById(R.id.cell12);
                break;
            case 2:
                cell2 = (TextView) findViewById(R.id.cell13);
                break;
            case 3:
                cell2 = (TextView) findViewById(R.id.cell21);
                break;
            case 4:
                cell2 = (TextView) findViewById(R.id.cell22);
                break;
            case 5:
                cell2 = (TextView) findViewById(R.id.cell23);
                break;
            case 6:
                cell2 = (TextView) findViewById(R.id.cell31);
                break;
            case 7:
                cell2 = (TextView) findViewById(R.id.cell32);
                break;
            case 8:
                cell2 = (TextView) findViewById(R.id.cell33);
                break;
        }
        cell2.setText(board.SYMBOL[board.COMPUTER]);
        board.board[x] = board.COMPUTER;
        checkWhoWin();
    }

    public void checkWhoWin(){
        if(board.checkWin(board.PLAYER)){
            isPlay = false;
            Log.v("CHECK", "1 WON!");
        }
        if(board.checkWin(board.COMPUTER)){
            isPlay = false;
            Log.v("CHECK", "2 WON!");
        }
        if(board.nextMove(board.PLAYER) == -1 && board.nextMove(board.COMPUTER) == -1){
            isPlay = false;
            Log.v("CHECK", "DRAW");
        }
    }
}
