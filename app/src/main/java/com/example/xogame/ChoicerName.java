package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChoicerName extends AppCompatActivity implements TextWatcher {
    EditText first,secend;

    private  Language lang;
    ImageView X_btn,O_btn;
    private String curLang;
    String selection;
    Button btn;
    String mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lang =new Language(getBaseContext());
        try {
            curLang=     lang.GetLocal();

        }catch (Exception e){
            lang.setLocale("en");
            curLang=lang.curlang;

        }
        setContentView(R.layout.activity_choicer_name);
        Intent i=getIntent();
         mode=i.getStringExtra("mode");
      X_btn=findViewById(R.id.Ximage);
      O_btn=findViewById(R.id.Oimage);
        first=findViewById(R.id.firstName);
        secend=findViewById(R.id.SecendName);

        btn=findViewById(R.id.continue_btn);
        btn.setEnabled(false);
        btn.setAlpha(.5f);
        first.addTextChangedListener(this);
        secend.addTextChangedListener(this);

        if(mode.equals("c")){
            secend.setEnabled(false);
            secend.setText(getString(R.string.Computer));
        }


      X_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              X_btn.setAlpha(1f); // Set opacity to 70%
              selection = "x";

              // Hide the O_btn
              O_btn.setAlpha(1.0f); // Ensure O_btn is fully opaque
              O_btn.setAlpha(.5f); // H
          }
      });
        O_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                O_btn.setAlpha(1f); // Set opacity to 70%
                selection = "o";

                // Hide the X_btn
                X_btn.setAlpha(1.0f); // Ensure X_btn is fully opaque
                X_btn.setAlpha(.5f); // H

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {
                if(selection==null){
                    Toast.makeText(ChoicerName.this, "Please Choose X OR O", Toast.LENGTH_SHORT).show();
                }else{



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
                }}}
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        String newlang=  lang.GetLocal();
        if (!curLang.equals(newlang)) {
            recreate();  // Restart the activity to apply the new language
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        btn.setAlpha(0.5f);
        btn.setEnabled(false);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!(mode.equals("c"))){
            if(!(first.getText().toString().isEmpty())&&!(secend.getText().toString().isEmpty())){
                btn.setEnabled(true);
                btn.setAlpha(1f);

            }


        }else {
            if(!(first.getText().toString().isEmpty())){
                btn.setEnabled(true);
                btn.setAlpha(1f);

            }

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}