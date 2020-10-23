package com.aditya.cricketrecord;

import android.provider.BaseColumns;

public final class ScoreContract  {
    private ScoreContract(){}
    public static class ScoreEntry implements BaseColumns{
        public static final String TABLE_NAME ="states";
        public static final String COLUMN_TEAM_A = "TeamA";
        public static final String COLUMN_TEAM_B = "TeamB";
        public static final String COLUMN_RUNS_A = "RunsA";
        public static final String COLUMN_RUNS_B = "RunsB";
        public static final String COLUMN_BALLS_A = "BallsA";
        public static final String COLUMN_BALLS_B = "BallsB";
        public static final String COLUMN_FOUR_A = "FourA";
        public static final String COLUMN_FOUR_B = "FourB";
        public static final String COLUMN_SIX_A = "SixA";
        public static final String COLUMN_SIX_B = "SixB";
        public static final String COLUMN_WICKET_A = "WicketA";
        public static final String COLUMN_WICKET_B = "WicketB";
        public static final String COLUMN_EXTRAS_A = "ExtrasA";
        public static final String COLUMN_EXTRAS_B = "ExtrasB";


    }
}
