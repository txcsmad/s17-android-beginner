package ahn.springproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ScarnesDiceActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarnes_dice);

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.dice_image);
    }

    public void changeDice(View v) {
        int randomNumber = (int) (Math.random() * 6 + 1);
        switch(randomNumber) {
            case 1: imageView.setImageResource(R.drawable.dice1);
                    break;
            case 2: imageView.setImageResource(R.drawable.dice2);
                    break;
            case 3: imageView.setImageResource(R.drawable.dice3);
                    break;
            case 4: imageView.setImageResource(R.drawable.dice4);
                    break;
            case 5: imageView.setImageResource(R.drawable.dice5);
                    break;
            case 6: imageView.setImageResource(R.drawable.dice6);
                    break;
        }
    }
}
