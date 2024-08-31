package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChoicerName extends AppCompatActivity {
    EditText first,secend;
    private RadioGroup radioGroup;
    private RadioButton radioX, radioO;
    String selection;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicer_name);
        radioGroup = findViewById(R.id.RadioGroup);
        Intent i=getIntent();
        String mode=i.getStringExtra("mode");
        radioX = findViewById(R.id.RadioX);
        radioO = findViewById(R.id.RadioO);
        first=findViewById(R.id.firstName);
        secend=findViewById(R.id.SecendName);
        btn=findViewById(R.id.continue_btn);
        if(mode.equals("c")){
            secend.setEnabled(false);
            secend.setText("Computer");
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                selection = selectedRadioButton.getText().toString();
                Toast.makeText(ChoicerName.this, "You selected: " + selection, Toast.LENGTH_SHORT).show();
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equals("c")){
                    Intent i =new Intent(getBaseContext(), Computerscreen.class);
                    i.putExtra("select",selection.toLowerCase());
                    i.putExtra("first",first.getText().toString());

                    startActivity(i);
                    secend.setEnabled(false);
                }else{
                    Intent i =new Intent(getBaseContext(), friend_screen.class);
                    i.putExtra("select",selection.toLowerCase());
                    i.putExtra("first",first.getText().toString());
                    i.putExtra("secend",secend.getText().toString());
                    startActivity(i);
                }}
        });
    }
}