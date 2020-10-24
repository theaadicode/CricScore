package com.aditya.cricketrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button button,prev;
    String teamA,teamB;
    SeekBar seekBar;
    int over;
    TextView overs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button11);
        overs = findViewById(R.id.overs);
        seekBar = findViewById(R.id.over);
        seekBar.setMax(15);
        seekBar.setProgress(1);
        over = 1;
        overs.setText(String.format(getString(R.string.overs),over));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                over = progress;
                overs.setText(String.format(getString(R.string.overs),over));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamA = editText1.getText().toString();
                teamB = editText2.getText().toString();
                if(teamA.isEmpty() || teamB.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Fill The Team Name",Toast.LENGTH_SHORT).show();
                }
                else if(over == 0){
                    Toast.makeText(getApplicationContext(),over+" Overs Cannot Be Selected.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    intent.putExtra("teamA", teamA);
                    intent.putExtra("teamB", teamB);
                    intent.putExtra("over",over);
                    startActivity(intent);
                }
            }
        });
        prev = findViewById(R.id.previous_states);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,DataActivity.class);
                startActivity(intent);
            }
        });
    }
}