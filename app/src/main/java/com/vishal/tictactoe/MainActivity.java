package com.vishal.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red
    //2 = unplayable

    boolean gameActive = true;

    int game[]={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int activePlayer = 0;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());

        if (game[tapped] == 2 && gameActive) {
            game[tapped] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }

            else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int [] winningPosition : winningPositions){
                if (game[winningPosition[0]] == game[winningPosition[1]] &&
                        game[winningPosition[1]] == game[winningPosition[2]] &&
                        game[winningPosition[0]]!=2) {

                    gameActive = false;
                    String winner = "Red";

                    if(game[winningPosition[0]] == 0){
                        winner = "Yellow";

                    }

                    //Someone has won

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");

                  final  LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    layout.postDelayed(new Runnable() {
                        public void run() {
                            layout.setVisibility(View.VISIBLE);
                        }
                    }, 500);

                } else {

                    boolean gameOver = true;

                    for (int count : game) {

                        if(count == 2) gameOver = false;
                    }

                    if (gameOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");

                        final  LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                        layout.postDelayed(new Runnable() {
                            public void run() {
                                layout.setVisibility(View.VISIBLE);
                            }
                        }, 500);
                    }
                }
            }
        }

    }

    public void playAgain(View view) {

        gameActive = true;
        final  LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i = 0; i<game.length; i++) {
            game[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
