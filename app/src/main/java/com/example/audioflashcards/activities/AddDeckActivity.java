package com.example.audioflashcards.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.audioflashcards.R;

public class AddDeckActivity extends BaseActivity {

    private EditText editDeck;
    private Button addFlashcards;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deck);

        this.addFlashcards = (Button) findViewById(R.id.button_add_flashcards);
        this.editDeck = (EditText) findViewById(R.id.edit_deck);
        this.addFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAddFlashcard();
            }
        });
    }

    public void toAddFlashcard() {
        String deck = this.editDeck.getText().toString();
        Intent intent = new Intent(this, AddFlashcardActivity.class);

        Bundle b = new Bundle();
        b.putString("deck", deck);
        intent.putExtras(b);

        startActivity(intent);
    }
}


