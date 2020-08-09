package com.aditya.cricketrecord;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView teamAScoreCard,teamBScoreCard,teamAName,teamBName;
    Button singleForA,fourForA,sixForA,wickerOfA,extraA,singleForB,fourForB,sixForB,wickerOfB,extraB,resetScore,dotA,dotB,restartGame;
    Score teamAScore,teamBScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamAScoreCard = findViewById(R.id.score_card_A);         teamBScoreCard = findViewById(R.id.score_card_B);
        teamAName = findViewById(R.id.team_A_name);               teamBName = findViewById(R.id.team_B_name);
        singleForA = findViewById(R.id.single_A);                 singleForB = findViewById(R.id.single_B);
        fourForA = findViewById(R.id.four_A);                     fourForB = findViewById(R.id.four_B);
        sixForA = findViewById(R.id.six_A);                       sixForB = findViewById(R.id.six_B);
        wickerOfA = findViewById(R.id.wicket_A);                  wickerOfB = findViewById(R.id.wicket_B);
        extraA = findViewById(R.id.extra_A);                      extraB = findViewById(R.id.extra_B);
        dotA = findViewById(R.id.dot_A);                          dotB = findViewById(R.id.dot_B);
        resetScore = findViewById(R.id.reset_score);              restartGame = findViewById(R.id.restart_game);

        Intent intent = getIntent();
        String teamA = intent.getStringExtra("teamA");
        String teamB = intent.getStringExtra("teamB");
        teamAName.setText(teamA);
        teamBName.setText(teamB);

        teamAScore = new Score();
        teamBScore = new Score();
        teamAScoreCard.setText("Runs : " + teamAScore.getRuns() + "\nBalls: " + teamAScore.getBalls() + "\nWickets: " + teamAScore.getWicket() );
        teamBScoreCard.setText("Runs : " + teamBScore.getRuns() + "\nBalls: " + teamBScore.getBalls() + "\nWickets: " + teamBScore.getWicket() );


        dotA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.balls();
                displayA();
            }
        });

        singleForA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.single();
                teamAScore.balls();
                displayA();
            }
        });

        fourForA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.four();
                teamAScore.balls();
                displayA();
            }
        });

        sixForA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.six();
                teamAScore.balls();
                displayA();
            }
        });

        wickerOfA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.wicket();
                teamAScore.balls();
                displayA();
            }
        });

        extraA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.single();
                displayA();
            }
        });

        dotB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.balls();
                displayB();
            }
        });

        singleForB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.single();
                teamBScore.balls();
                displayB();
            }
        });

        fourForB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.four();
                teamBScore.balls();
                displayB();
            }
        });

        sixForB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.six();
                teamBScore.balls();
                displayB();
            }
        });

        wickerOfB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.wicket();
                teamBScore.balls();
                displayB();
            }
        });

        extraB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.single();
                displayB();
            }
        });

        resetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogCLickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                teamAScore.reset();
                                teamBScore.reset();
                                displayA();
                                displayB();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are You Want to Reset Score?").setPositiveButton("Yes",dialogCLickListener)
                        .setNegativeButton("No",dialogCLickListener).show();
            }
        });

        restartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogCLickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are You Want to Restart Match?").setPositiveButton("Yes",dialogCLickListener)
                        .setNegativeButton("No",dialogCLickListener).show();
            }
        });
    }

    protected void displayA(){
        teamAScoreCard.setText("Runs : " + teamAScore.getRuns() + "\nBalls: " + teamAScore.getBalls() + "\nWickets: " + teamAScore.getWicket() );
    }
    protected void displayB(){
        teamBScoreCard.setText("Runs : " + teamBScore.getRuns() + "\nBalls: " + teamBScore.getBalls() + "\nWickets: " + teamBScore.getWicket() );
    }
}
