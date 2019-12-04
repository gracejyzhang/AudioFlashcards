package com.example.audioflashcards.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.audioflashcards.GestureListener;
import com.example.audioflashcards.R;
import com.example.audioflashcards.database.DBHelper;
import com.example.audioflashcards.database.Flashcard;
import com.example.audioflashcards.fragments.DeckTitlepageFragment;
import com.example.audioflashcards.fragments.FlashcardFragment;

import java.util.Locale;

public class FlashcardActivity extends BaseActivity {

    private DBHelper db;
    private Cursor flashcards;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String deck;
    private FlashcardFragment newFragment;
    private TextToSpeech tts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        Bundle b = getIntent().getExtras();
        this.deck = "Deck";
        if (b!= null) {
            this.deck =  b.getString("deck");
        }

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.CANADA);
            }
        });

        this.db = new DBHelper(this);
        this.flashcards = db.getFlashcards(deck);

        this.fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = fragmentManager.beginTransaction();

        DeckTitlepageFragment titleFragment = new DeckTitlepageFragment();
        Bundle t = new Bundle();
        t.putString("deck", this.deck);
        titleFragment.setArguments(t);
        FragmentTransaction tTransaction = getSupportFragmentManager().beginTransaction();
        tTransaction.replace(R.id.placeholder, titleFragment);
        tTransaction.commit();

        findViewById(R.id.touch).setOnTouchListener(new GestureListener(FlashcardActivity.this) {
            public void onSwipeLeft() {
                if (flashcards.moveToNext()) {
                    String question = flashcards.getString(flashcards.getColumnIndex(Flashcard.COLUMN_QUESTION));
                    String answer = flashcards.getString(flashcards.getColumnIndex(Flashcard.COLUMN_ANSWER));
                    int id = flashcards.getInt(flashcards.getColumnIndex(Flashcard.COLUMN_ID));

                    newFragment = new FlashcardFragment();
                    Bundle n = new Bundle();
                    n.putString("deck", deck);
                    n.putString("question", question);
                    n.putString("answer", answer);
                    n.putInt("id", id);
                    newFragment.setArguments(n);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.placeholder, newFragment);
                    // transaction.addToBackStack(null);
                    tts.speak(question, TextToSpeech.QUEUE_FLUSH, null, null);
                    transaction.commit();
                } else {
                    flashcards.close();
                    db.close();
                    Intent intent = new Intent(FlashcardActivity.this, MainActivity.class);
                    startActivity(intent);                }
            }

            public void onSingleClick() {
                if (flashcards.getPosition() != -1) {
                    newFragment.onShowAnswer(tts);
                }
            }
        });
    }

    public void deleteFlashcard(View view) {
        Toast.makeText(this, "Delete flashcard", Toast.LENGTH_SHORT).show();
    }

    public void editFlashcard(View view) {
        Toast.makeText(this, "Edit flashcard", Toast.LENGTH_LONG).show();
    }
}
