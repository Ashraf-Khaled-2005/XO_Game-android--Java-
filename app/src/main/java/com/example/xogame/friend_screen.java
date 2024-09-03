package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class friend_screen extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer player;
    TextView playerstatus;
    Button [] btns=new Button[9];
    ImageView player1kind,player2kind;
    Button restgame;
    TextView player1,player2;
    Boolean activieplayer,issoundon;
    String selectedOption;
    private  Shared_pref sharedPref;

    int player1score,player2score;
    int rount;
    Drawable defaultBackground;

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
        sharedPref=new Shared_pref(this);
        issoundon=sharedPref.GetBool("IsSoundOn");

        setContentView(R.layout.activity_friend_screen);

        restgame=findViewById(R.id.Rest_btn);
        playerstatus=findViewById(R.id.player_status);
        player1=findViewById(R.id.player1);
        player2=findViewById(R.id.player2);
        Intent intent = getIntent();
        player1kind=findViewById(R.id.player1kind);
player2kind=findViewById(R.id.player2kind);
        // Get the extras passed from the previous activity
         selectedOption = intent.getStringExtra("select");
        String firstName = intent.getStringExtra("first");
        String secendName = intent.getStringExtra("secend");
        player1.setText(firstName);
        player2.setText(secendName);
if(selectedOption.equals("x")){
    player1kind.setImageResource(R.drawable.groupx);
    player2kind.setImageResource(R.drawable.groupo);
}else{
    player1kind.setImageResource(R.drawable.groupo);
    player2kind.setImageResource(R.drawable.groupx);


}


        for (int i = 0; i < 9; i++) {
            String btnID = "btn_" + i;
            int resID = getResources().getIdentifier(btnID, "id", getPackageName());
            btns[i] = findViewById(resID);
            btns[i].setOnClickListener(this); // Set the OnClickListener for each button

        }
        defaultBackground = btns[0].getBackground();

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
            if(selectedOption.equals("x"))
//            ((Button) view).setText("X");
                ((Button) view).setBackground(getDrawable(R.drawable.groupx));

            else{
                ((Button) view).setBackground(getDrawable(R.drawable.groupo));


            }
            if(issoundon)
                 player.start();
            gamestate[id]=1;
        }else{
            if(selectedOption.equals("x"))
                ((Button) view).setBackground(getDrawable(R.drawable.groupo));

//            ((Button) view).setText("O");
            else
                ((Button) view).setBackground(getDrawable(R.drawable.groupx));
            if(issoundon)
                  player.start();

            gamestate[id]=2;
        }
        rount++;
        if (checkwinner()) {
            ImageView winGif = findViewById(R.id.gif);
            winGif.setVisibility(View.VISIBLE);
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
            winGif.postDelayed(new Runnable() {
                @Override
                public void run() {
                    winGif.setVisibility(View.GONE);
                }
            }, 2000);

        } else if (rount == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            disableAllButtons();

        } else {
            activieplayer = !activieplayer;
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
            btns[i].setBackground(defaultBackground);

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

}