package com.example.audioflashcards.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.audioflashcards.GestureListener;
import com.example.audioflashcards.R;
import com.example.audioflashcards.adapters.FlashcardAdapter;
import com.example.audioflashcards.database.DBHelper;
import com.example.audioflashcards.database.Flashcard;
import java.util.ArrayList;

public class SelectRecyclerActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ArrayList<Flashcard> flashcards = new ArrayList<>();
    private FlashcardAdapter adapter;
    private Button button;
    private Context context = this;
    private DBHelper db;
    private String deck;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Bundle b = getIntent().getExtras();
        this.deck = b.getString("deck");

        this.db = new DBHelper(this);
        this.flashcards = this.db.getFlashcardList(this.deck);

        this.button = (Button) findViewById(R.id.delete_activity);
        this.button.setText("Delete flashcards");
        this.recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        this.adapter = new FlashcardAdapter(this, this.flashcards);
        this.recyclerView.setAdapter(adapter);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Flashcard> selectedList = adapter.getSelected();

                if (selectedList.size() > 0) {
                    for (int i = 0; i < selectedList.size(); i++) {
                        db.deleteFlashcard(selectedList.get(i));
                    }
                    Toast.makeText(context,"Successfully deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "No Selection", Toast.LENGTH_SHORT).show();
                }

                adapter.setFlashcards(db.getFlashcardList(deck));
            }
        });
    }
}
