package com.example.audioflashcards.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.audioflashcards.R;
import com.example.audioflashcards.adapters.SingleSelectAdapter;
import com.example.audioflashcards.database.DBHelper;
import com.example.audioflashcards.database.Flashcard;

import java.util.ArrayList;

public class SingleSelectActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ArrayList<Flashcard> flashcards = new ArrayList<>();
    private SingleSelectAdapter adapter;
    String deck;
    DBHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);

        Bundle b = getIntent().getExtras();
        this.deck = b.getString("deck");

        this.db = new DBHelper(this);
        this.flashcards = this.db.getFlashcardList(this.deck);

        this.recyclerView = (RecyclerView) findViewById(R.id.single_select);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        this.adapter = new SingleSelectAdapter(this, this.flashcards);
        this.recyclerView.setAdapter(adapter);
    }

}
