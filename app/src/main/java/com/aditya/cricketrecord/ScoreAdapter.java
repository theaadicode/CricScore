package com.aditya.cricketrecord;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.cricketrecord.ScoreContract.ScoreEntry;

public class ScoreAdapter extends CursorRecyclerViewAdapter<ScoreAdapter.ScoreViewHolder> {
    public ScoreAdapter(Context context, Cursor cursor){
    super(context,cursor);
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_score,parent,false);
        return new ScoreViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ScoreViewHolder viewHolder, Cursor cursor) {
        String teamAName = cursor.getString(cursor.getColumnIndex(ScoreEntry.COLUMN_TEAM_A));
        String teamBName = cursor.getString(cursor.getColumnIndex(ScoreEntry.COLUMN_TEAM_B));
        int teamARun = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_RUNS_A));
        int teamBRun = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_RUNS_B));
        int teamABalls = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_BALLS_A));
        int teamBBalls = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_BALLS_B));
        int teamAFour = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_FOUR_A));
        int teamBFour = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_FOUR_B));
        int teamASix = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_SIX_A));
        int teamBSix = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_SIX_B));
        int teamAWicket = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_WICKET_A));
        int teamBWicket = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_WICKET_B));
        int teamAExtras = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_EXTRAS_A));
        int teamBExtras = cursor.getInt(cursor.getColumnIndex(ScoreEntry.COLUMN_EXTRAS_B));

        viewHolder.teamA.setText(teamAName);
        viewHolder.teamB.setText(teamBName);

        viewHolder.scoreA.setText(teamARun + "\\" + teamAWicket + "("+teamABalls+")");
        viewHolder.scoreB.setText(teamBRun + "\\" + teamBWicket + "("+teamBBalls+")");

        viewHolder.boundA.setText(teamAFour + "\\"+teamASix);
        viewHolder.boundB.setText(teamBFour + "\\"+teamBSix);

        viewHolder.extraA.setText(String.valueOf(teamAExtras));
        viewHolder.extraB.setText(String.valueOf(teamBExtras));
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder{
        public TextView teamA,teamB,scoreA,scoreB,boundA,boundB,extraA,extraB;
        public ScoreViewHolder(View view){
            super(view);
            teamA = view.findViewById(R.id.team_name_A);
            teamB = view.findViewById(R.id.team_name_B);
            scoreA = view.findViewById(R.id.score_A);
            scoreB = view.findViewById(R.id.score_B);
            boundA = view.findViewById(R.id.boundaries_A);
            boundB = view.findViewById(R.id.boundaries_B);
            extraA = view.findViewById(R.id.extras_A);
            extraB = view.findViewById(R.id.extras_B);
        }
    }
}
