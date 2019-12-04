package com.example.audioflashcards.activities;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.audioflashcards.adapters.DeckAdapter;
import com.example.audioflashcards.R;
import com.example.audioflashcards.database.DBHelper;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter deckAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DBHelper db;
    private String[] dataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DBHelper(this);
        this.dataset = db.getDecks();

        this.recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        deckAdapter = new DeckAdapter(dataset, this);
        //deckAdapter.setClickListener(this);
        recyclerView.setAdapter(deckAdapter);

    }

    public void addActivity(View view) {
        Intent intent = new Intent(this, AddDeckActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean displayTitle() {
        return true;
    }

    // add starred (boolean, default = false) field in data table
    // make edit card and delete card methods on card and by search (is on card okay changing while cursor in place?)
    // see close database
}
