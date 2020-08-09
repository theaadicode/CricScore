package com.aditya.cricketrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button button;
    String teamA,teamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button11);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamA = editText1.getText().toString();
                teamB = editText2.getText().toString();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.putExtra("teamA",teamA);
                intent.putExtra("teamB",teamB);
                startActivity(intent);
            }
        });

    }
}