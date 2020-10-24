package com.aditya.cricketrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    SQLiteDatabase database;
    Cursor cursor;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Previous Match Record");

        ScoreDBHelper dbHelper = new ScoreDBHelper(this);
        database = dbHelper.getReadableDatabase();

        recyclerView = findViewById(R.id.recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        cursor = database.query(ScoreContract.ScoreEntry.TABLE_NAME,null,null,null,null,null,null);

        resources = getResources();

        adapter = new ScoreAdapter(this,cursor,resources);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void reload(){
        cursor = database.query(ScoreContract.ScoreEntry.TABLE_NAME,null,null,null,null,null,null);

        adapter = new ScoreAdapter(this,cursor,resources);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.refresh){
            reload();
            return true;
        }
        return false;
    }
}