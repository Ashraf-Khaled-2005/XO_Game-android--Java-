package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private String mode;
    ImageView image;
    Button friend_btn,computer_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=findViewById(R.id.com_fri);
        friend_btn=findViewById(R.id.friend_btn);
        computer_btn=findViewById(R.id.computer_btn);
        friend_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.friend);
                if(image.getVisibility()==View.INVISIBLE)
                    image.setVisibility(View.VISIBLE);

                Intent i =new Intent(getBaseContext(), ChoicerName.class);
                i.putExtra("mode","f");
                startActivity(i);
            }

        });


        computer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.robot);
                if(image.getVisibility()==View.INVISIBLE)
                    image.setVisibility(View.VISIBLE);
                Intent i =new Intent(getBaseContext(), ChoicerName.class);
                i.putExtra("mode","c");
                startActivity(i);
            }

        });
    }
}