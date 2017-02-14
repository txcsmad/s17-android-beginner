package ahn.springproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScarnesDiceActivity extends AppCompatActivity {

    ImageView imageView;

    TextView playerTextView;
    TextView compTextView;
    TextView pointsTextView;

    GameObject gameObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarnes_dice);

        imageView = (ImageView) findViewById(R.id.dice_image);

        playerTextView = (TextView) findViewById(R.id.player_text_view);
        compTextView = (TextView) findViewById(R.id.comp_text_view);
        pointsTextView = (TextView) findViewById(R.id.points_textview);

        gameObject = new GameObject(getApplicationContext());
    }


    public void reset(View view) {
        gameObject.reset();
        updateUI();
    }

    public void hold(View view) {
        gameObject.hold();
        updateUI();
    }

    public void roll(View view) {
        gameObject.roll();
        updateUI();
    }

    public void updateUI() {
        int pScore = gameObject.getPlayerScore();
        int cScore = gameObject.getCompScore();
        int points = gameObject.getPoints();
        int roll = gameObject.getRoll();

        playerTextView.setText("Player: " + pScore);
        compTextView.setText("Computer: " + cScore);
        pointsTextView.setText("Points in Store: " + points);

        switch(roll) {
            case 1:
                imageView.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.dice6);
                break;
        }

        if(pScore >= 100) {
            Toast.makeText(getApplicationContext(), "Player has won!", Toast.LENGTH_SHORT).show();
            reset(null);
        }
        else if (cScore >= 100) {
            Toast.makeText(getApplicationContext(), "Computer has won!", Toast.LENGTH_SHORT).show();
            reset(null);
        }
    }
}
