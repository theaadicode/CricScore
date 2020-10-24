package com.aditya.cricketrecord;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.cricketrecord.ScoreContract.ScoreEntry;

public class ScoreAdapter extends CursorRecyclerViewAdapter<ScoreAdapter.ScoreViewHolder> {
    private Context context;
    private Resources resources;
    public ScoreAdapter(Context context, Cursor cursor, Resources resources){
    super(context,cursor);
    this.context = context;
    this.resources = resources;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_score,parent,false);
        return new ScoreViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ScoreViewHolder viewHolder, Cursor cursor) {
        ScoreDBHelper dbHelper = new ScoreDBHelper(context);
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final long id = cursor.getLong(cursor.getColumnIndex(ScoreEntry._ID));
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
        String date = cursor.getString(cursor.getColumnIndex(ScoreEntry.COLUMN_DATE));
        String time = cursor.getString(cursor.getColumnIndex(ScoreEntry.COLUMN_TIME));

        viewHolder.teamA.setText(teamAName);
        viewHolder.teamB.setText(teamBName);

        viewHolder.scoreA.setText(resources.getString(R.string.score_set,teamARun,teamAWicket,(teamABalls/6)));
        viewHolder.scoreB.setText(resources.getString(R.string.score_set,teamBRun,teamBWicket,(teamBBalls/6)));

        viewHolder.boundA.setText(resources.getString(R.string.boundary_set,teamAFour,teamASix));
        viewHolder.boundB.setText(resources.getString(R.string.boundary_set,teamBFour,teamBSix));

        viewHolder.extraA.setText(String.valueOf(teamAExtras));
        viewHolder.extraB.setText(String.valueOf(teamBExtras));

        viewHolder.date.setText(date);
        viewHolder.time.setText(time);

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                int delete = database.delete(ScoreEntry.TABLE_NAME, ScoreEntry._ID + "=?", new String[]{String.valueOf(id)});
                                Toast.makeText(context,"Deleted " + delete + " row",Toast.LENGTH_SHORT).show();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:  break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are You Want To Delete this Item?").setPositiveButton("Yes",onClickListener)
                        .setNegativeButton("No",onClickListener).show();
                return false;
            }
        });
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder{
        public TextView teamA,teamB,scoreA,scoreB,boundA,boundB,extraA,extraB,date,time;
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
            date = view.findViewById(R.id.date_rec);
            time = view.findViewById(R.id.time_rec);
        }
    }
}
