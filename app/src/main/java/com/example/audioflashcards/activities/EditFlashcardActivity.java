package com.example.audioflashcards.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.audioflashcards.R;
import com.example.audioflashcards.database.DBHelper;
import com.example.audioflashcards.database.Flashcard;

public class EditFlashcardActivity extends BaseActivity {

    private DBHelper db;
    private Button edit;
    private EditText editquestion, editanswer;
    private String deck, question, answer;
    private int id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        Bundle b = getIntent().getExtras();
        this.deck = b.getString("deck");
        this.question = b.getString("question");
        this.answer = b.getString("answer");
        this.id = b.getInt("id");

        this.db = new DBHelper(this);
        this.edit = (Button) findViewById(R.id.add);
        this.edit.setText("Save");
        this.editquestion = (EditText) findViewById(R.id.editquestion);
        this.editquestion.setText(this.question);
        this.editanswer = (EditText) findViewById(R.id.editanswer);
        this.editanswer.setText(this.answer);

        this.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFlashCard();
            }
        });
    }

    public void editFlashCard() {
        String question = this.editquestion.getText().toString();
        String answer = this.editanswer.getText().toString();
        Flashcard temp = new Flashcard(this.id, question, answer, this.deck);

        long edited = this.db.updateFlashcard(temp);

        if (edited != -1) {
            Intent intent = new Intent(this, SingleSelectActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("deck", this.deck);
            intent.putExtras(bundle);

            startActivity(intent);
        } else {
            Toast.makeText(this, "The change was not successful", Toast.LENGTH_LONG).show();
        }
    }
}
