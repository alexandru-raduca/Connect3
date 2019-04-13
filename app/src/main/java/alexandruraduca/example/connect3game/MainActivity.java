package alexandruraduca.example.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;   //0: yellow, 1: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;

    Button playAgainButton ;

    TextView winnerTextView;

    public void dropIn(View view) {

        int flag;


        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        ImageView counter = (ImageView) view;   //the view I tap on


        //get tapped tag. it's going to be use for gamestate array
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameActive && gameState[tappedCounter] == 2) {

            counter.setTranslationY(-1500);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }


            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    //Someone has won

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    gameActive = false;


                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
            
            for (flag = 0; flag < gameState.length; flag++) {
                if (gameState[flag] == 2) break;
            }

            if (flag == gameState.length && gameActive == true) {
                winnerTextView.setText("No one has won!");

                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view){
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
           ImageView counter = (ImageView) gridLayout.getChildAt(i);
           counter.setImageDrawable(null);
        }

        activePlayer = 0;   //0: yellow, 1: red, 2: empty

        for(int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
