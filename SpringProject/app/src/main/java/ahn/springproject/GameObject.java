package ahn.springproject;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Ali on 2/9/2017.
 */

public class GameObject {
    private int playerScore = 0;
    private int compScore = 0;
    private int points = 0;
    private int roll = 1;

    private Context context;

    public GameObject(Context context) {
        this.context = context;
    }

    public void roll() {
        this.roll = (int) (Math.random() * 6 + 1);

        if(roll != 1)
            this.points += roll;
        else {
            this.points = 0;
            this.compTurn();
        }
    }

    public void hold() {
        this.playerScore += this.points;
        this.points = 0;
        this.compTurn();
    }

    public void reset() {
        this.playerScore = 0;
        this.compScore = 0;
        this.points = 0;
    }

    private void compTurn() {
        int turns = (int) (Math.random() * 4) + 2;

        while(turns > 0){
            roll = (int) (Math.random()*6)+1;

            if(roll == 1) {
                this.points = 0;
                Toast.makeText(context, "Computer rolled a 1!", Toast.LENGTH_SHORT).show();
                break;
            }
            this.points += roll;
            turns--;
        }
        this.compScore += this.points;
        this.points = 0;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getCompScore() {
        return compScore;
    }

    public int getPoints() {
        return points;
    }

    public int getRoll() {
        return roll;
    }
}
