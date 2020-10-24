package com.aditya.cricketrecord;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.cricketrecord.ScoreContract.ScoreEntry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView teamAScoreCard,teamBScoreCard,teamAName,teamBName;
    Button singleForA,fourForA,sixForA,wickerOfA,extraA,singleForB,fourForB,sixForB,wickerOfB,extraB,resetScore,dotA,dotB,restartGame,saveGame;
    Score teamAScore,teamBScore;
    ScoreDBHelper dbHelper;
    SQLiteDatabase database;
    String teamA,teamB,scoreA,scoreB;
    int over;
    boolean change = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Play Match");

        teamAScoreCard = findViewById(R.id.score_card_A);         teamBScoreCard = findViewById(R.id.score_card_B);
        teamAName = findViewById(R.id.team_A_name);               teamBName = findViewById(R.id.team_B_name);
        singleForA = findViewById(R.id.single_A);                 singleForB = findViewById(R.id.single_B);
        fourForA = findViewById(R.id.four_A);                     fourForB = findViewById(R.id.four_B);
        sixForA = findViewById(R.id.six_A);                       sixForB = findViewById(R.id.six_B);
        wickerOfA = findViewById(R.id.wicket_A);                  wickerOfB = findViewById(R.id.wicket_B);
        extraA = findViewById(R.id.extra_A);                      extraB = findViewById(R.id.extra_B);
        dotA = findViewById(R.id.dot_A);                          dotB = findViewById(R.id.dot_B);
        resetScore = findViewById(R.id.reset_score);              restartGame = findViewById(R.id.restart_game);
        saveGame = findViewById(R.id.save_game);

        dbHelper = new ScoreDBHelper(this);

        Intent intent = getIntent();
        teamA = intent.getStringExtra("teamA");
        teamB = intent.getStringExtra("teamB");
        over = intent.getIntExtra("over",1);
        teamAName.setText(teamA);
        teamBName.setText(teamB);

        teamAScore = new Score();
        teamBScore = new Score();
        scoreA = getString(R.string.score,teamAScore.getRuns(),teamAScore.getBalls(),teamAScore.getWicket(),teamAScore.getFour(),teamAScore.getSix(),teamAScore.getExtras());
        scoreB = getString(R.string.score,teamBScore.getRuns(),teamBScore.getBalls(),teamBScore.getWicket(),teamBScore.getFour(),teamBScore.getSix(),teamBScore.getExtras());
        teamAScoreCard.setText(scoreA);
        teamBScoreCard.setText(scoreB);


        dotA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.balls();
                displayA();
                CheckForBallsA();
            }
        });

        singleForA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.single();
                teamAScore.balls();
                displayA();
                CheckForBallsA();
            }
        });

        fourForA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.four();
                teamAScore.balls();
                displayA();
                CheckForBallsA();
            }
        });

        sixForA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.six();
                teamAScore.balls();
                displayA();
                CheckForBallsA();
            }
        });

        wickerOfA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.wicket();
                teamAScore.balls();
                displayA();
                checkForWicketA();
                CheckForBallsA();
            }
        });

        extraA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore.extras();
                displayA();
            }
        });

        dotB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.balls();
                displayB();
                CheckForBallsB();
            }
        });

        singleForB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.single();
                teamBScore.balls();
                displayB();
                CheckForBallsB();
            }
        });

        fourForB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.four();
                teamBScore.balls();
                displayB();
                CheckForBallsB();
            }
        });

        sixForB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.six();
                teamBScore.balls();
                displayB();
                CheckForBallsB();
            }
        });

        wickerOfB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.wicket();
                teamBScore.balls();
                displayB();
                checkForWicketB();
                CheckForBallsB();
            }
        });

        extraB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore.extras();
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
                                change = false;
                                displayA();
                                displayB();
                                doEnable();
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
        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change = false;
                database = dbHelper.getWritableDatabase();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String match_date = simpleDateFormat.format(date);
                simpleDateFormat = new SimpleDateFormat("hh:mm a");
                String match_time = simpleDateFormat.format(date);


                ContentValues contentValues = new ContentValues();
                contentValues.put(ScoreEntry.COLUMN_TEAM_A,teamA);
                contentValues.put(ScoreEntry.COLUMN_TEAM_B,teamB);
                contentValues.put(ScoreEntry.COLUMN_RUNS_A,teamAScore.getRuns());
                contentValues.put(ScoreEntry.COLUMN_RUNS_B,teamBScore.getRuns());
                contentValues.put(ScoreEntry.COLUMN_BALLS_A,teamAScore.getBalls());
                contentValues.put(ScoreEntry.COLUMN_BALLS_B,teamBScore.getBalls());
                contentValues.put(ScoreEntry.COLUMN_FOUR_A,teamAScore.getFour());
                contentValues.put(ScoreEntry.COLUMN_FOUR_B,teamBScore.getFour());
                contentValues.put(ScoreEntry.COLUMN_SIX_A,teamAScore.getSix());
                contentValues.put(ScoreEntry.COLUMN_SIX_B,teamBScore.getSix());
                contentValues.put(ScoreEntry.COLUMN_WICKET_A,teamAScore.getWicket());
                contentValues.put(ScoreEntry.COLUMN_WICKET_B,teamBScore.getWicket());
                contentValues.put(ScoreEntry.COLUMN_EXTRAS_A,teamAScore.getExtras());
                contentValues.put(ScoreEntry.COLUMN_EXTRAS_B,teamBScore.getExtras());
                contentValues.put(ScoreEntry.COLUMN_DATE,match_date);
                contentValues.put(ScoreEntry.COLUMN_TIME,match_time);

                long row = database.insert(ScoreEntry.TABLE_NAME, null, contentValues);
                if(row == -1){
                    Toast.makeText(getApplicationContext(),"Error With Inserting!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Successfully Inserted!!",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
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
        if(change) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do You Want to Go Back Without Saving Match?").setPositiveButton("Yes", dialogCLickListener)
                    .setNegativeButton("No", dialogCLickListener).show();
        }
        else{finish();}
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
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
        if(change) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do You Want to Go Back Without Saving Match?").setPositiveButton("Yes", dialogCLickListener)
                    .setNegativeButton("No", dialogCLickListener).show();
        }
        else{finish();}
    }

    protected void displayA(){
        teamAScoreCard.setText(scoreA);
    }
    protected void displayB(){
        teamBScoreCard.setText(scoreB);
    }
    private void checkForWicketA(){
        if(teamAScore.getWicket() >= 10) {
            Toast.makeText(this,"Innings of "+teamA+" is End",Toast.LENGTH_SHORT).show();
            singleForA.setEnabled(false);
            fourForA.setEnabled(false);
            sixForA.setEnabled(false);
            wickerOfA.setEnabled(false);
            extraA.setEnabled(false);
            dotA.setEnabled(false);
        }
    }
    private void checkForWicketB(){
        if(teamBScore.getWicket() >= 10) {
            Toast.makeText(this,"Innings of "+teamB+" is End",Toast.LENGTH_SHORT).show();
            singleForB.setEnabled(false);
            fourForB.setEnabled(false);
            sixForB.setEnabled(false);
            wickerOfB.setEnabled(false);
            extraB.setEnabled(false);
            dotB.setEnabled(false);
        }
    }
    private void CheckForBallsA(){
        if(teamAScore.getBalls() >= over*6) {
            Toast.makeText(this,"Innings of "+teamA+" is End",Toast.LENGTH_SHORT).show();
            singleForA.setEnabled(false);
            fourForA.setEnabled(false);
            sixForA.setEnabled(false);
            wickerOfA.setEnabled(false);
            extraA.setEnabled(false);
            dotA.setEnabled(false);
        }
    }
    private void CheckForBallsB(){
        if(teamBScore.getBalls() >= over*6) {
            Toast.makeText(this,"Innings of "+teamB+" is End",Toast.LENGTH_SHORT).show();
            singleForB.setEnabled(false);
            fourForB.setEnabled(false);
            sixForB.setEnabled(false);
            wickerOfB.setEnabled(false);
            extraB.setEnabled(false);
            dotB.setEnabled(false);
        }
    }
    private void doEnable(){
        singleForA.setEnabled(true);
        fourForA.setEnabled(true);
        sixForA.setEnabled(true);
        wickerOfA.setEnabled(true);
        extraA.setEnabled(true);
        dotA.setEnabled(true);
        singleForB.setEnabled(true);
        fourForB.setEnabled(true);
        sixForB.setEnabled(true);
        wickerOfB.setEnabled(true);
        extraB.setEnabled(true);
        dotB.setEnabled(true);
    }
}
