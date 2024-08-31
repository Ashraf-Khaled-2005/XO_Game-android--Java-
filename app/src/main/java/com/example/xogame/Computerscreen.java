package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Computerscreen extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer player;
    TextView playerstatus;
    Button[] btns=new Button[9];
    Button restgame;
    TextView player1,player2;
    Boolean activieplayer;
    int player1score,player2score;
    int rount;
    // 1 player1
    //2 player2
    int[]gamestate={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [] [] Winningpos={
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computerscreen);
        restgame=findViewById(R.id.Rest_btn_com);
        playerstatus=findViewById(R.id.player_status_com);
        player1=findViewById(R.id.player1_com);
        player2=findViewById(R.id.player2_com);
        Intent intent = getIntent();

        // Get the extras passed from the previous activity
        String selectedOption = intent.getStringExtra("select");
        String firstName = intent.getStringExtra("first");
        player1.setText(firstName);
        for (int i = 0; i < 9; i++) {
            String btnID = "btn_com_" + i;
            int resID = getResources().getIdentifier(btnID, "id", getPackageName());
            btns[i] = findViewById(resID);
            btns[i].setOnClickListener(this); // Set the OnClickListener for each button

        }


        rount=0;
        activieplayer=true;

        restgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playagain();
            }
        });
    }

    @Override
    public void onClick(View view) {
        player=MediaPlayer.create(getBaseContext(),R.raw.sound);

        if(!(((Button)view).getText().toString().equals(""))){
            return;
        }
        String btn_id=view.getResources().getResourceEntryName(view.getId());
        int id=Integer.parseInt(btn_id.substring(btn_id.length()-1,btn_id.length()));
        if(activieplayer){
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#FFFFFF"));

            player.start();
            gamestate[id]=1;
        }else{
            ((Button) view).setText("O");
            player.start();

            gamestate[id]=2;
        }
        rount++;
        if (checkwinner()) {

            if (activieplayer) {
                player=MediaPlayer.create(getBaseContext(),R.raw.win);
                player.start();
                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
                player1score++;
                playerstatus.setText(player1score+":"+player2score);
            } else {
                player=MediaPlayer.create(getBaseContext(),R.raw.fail);

                Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();

                player.start();
                player2score++;
                playerstatus.setText(player1score+":"+player2score);
            }
            disableAllButtons();

        } else if (rount == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            disableAllButtons();

        } else {
            activieplayer = !activieplayer;
            if (!activieplayer) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(this::computerMove, 1000);
            }
        }


    }


    public  Boolean checkwinner(){
        for (int[] element :
                Winningpos) {
            if (gamestate[element[0]] == gamestate[element[1]] && gamestate[element[1]] == gamestate[element[2]] && gamestate[element[0]] != -1){
                return true;
            }

        }
        return false;
    }

    public  void playagain(){

        for (int i = 0; i < 9; i++) {
            btns[i].setText("");
            gamestate[i]=-1;
        }
        rount=0;
        activieplayer=true;
        ActiveAllButtons();
    }
    private void disableAllButtons() {
        for (Button btn : btns) {
            btn.setEnabled(false); // Disable each button
        }
    }
    private void ActiveAllButtons() {
        for (Button btn : btns) {
            btn.setEnabled(true); // Disable each button
        }
    }


    private void computerMove() {
        Random rand = new Random();
        int move;
        do {
            move = rand.nextInt(9);
        } while (gamestate[move] != -1);

        gamestate[move] = 2;
        btns[move].setText("O");

        rount++;
        if (checkwinner()) {
            player2score ++;
            playerstatus.setText(player1score+":"+player2score);
            player=MediaPlayer.create(getBaseContext(),R.raw.fail);

            Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();

            player.start();
            disableAllButtons();
        } else if (rount == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        } else {
            activieplayer = true; // Switch back to player's turn
        }
    }
}