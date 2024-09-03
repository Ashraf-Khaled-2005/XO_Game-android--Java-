package com.example.xogame;

import android.widget.Button;

public class GameInfo {
    Button[] btns=new Button[9];
    int[]gamestate={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [] [] Winningpos={
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}


    };
    int rount=0;





}
