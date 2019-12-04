package com.example.audioflashcards.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.audioflashcards.R;
import com.example.audioflashcards.database.DBHelper;

public class AddFlashcardActivity extends BaseActivity {

    private DBHelper db;
    private Button add;
    private EditText editquestion, editanswer;
    private String deck;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        this.deck = getIntent().getExtras().getString("deck");
        this.db = new DBHelper(this);

        this.add = (Button) findViewById(R.id.add);
        this.editquestion = (EditText) findViewById(R.id.editquestion);
        this.editanswer = (EditText) findViewById(R.id.editanswer);

        this.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plusFlashCard();
            }
        });

    }

    public void plusFlashCard() {
        String question = this.editquestion.getText().toString();
        String answer = this.editanswer.getText().toString();

        long added = this.db.addFlashCard(question, answer, this.deck);

        if (added != -1) {
            Toast.makeText(this, "Successfully added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();
        }
    }

}
