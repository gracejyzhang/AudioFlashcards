package com.example.audioflashcards.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.audioflashcards.R;
import com.example.audioflashcards.activities.EditFlashcardActivity;

public class FlashcardFragment extends Fragment {

    String question, answer, deck;
    int id;
    View flashcardLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        this.deck = b.getString("deck");
        this.question = b.getString("question");
        this.answer = b.getString("answer");
        this.id = b.getInt("id");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.flashcardLayout = inflater.inflate(R.layout.fragment_flashcard, container, false);

        TextView deckText = (TextView) this.flashcardLayout.findViewById(R.id.deckText);
        deckText.setText(this.deck);

        TextView questionText = (TextView) this.flashcardLayout.findViewById(R.id.questionText);
        questionText.setText(this.question);

        Button editButton = (Button) this.flashcardLayout.findViewById(R.id.button_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFlashcard();
            }
        });

        Button deleteButton = (Button) this.flashcardLayout.findViewById(R.id.button_delete);
        //use interface????

        return this.flashcardLayout;
    }

    public void onShowAnswer(TextToSpeech tts) {
        TextView answerText = (TextView) this.flashcardLayout.findViewById(R.id.answerText);
        answerText.setText(this.answer);
        tts.speak(this.answer, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void editFlashcard() {
        Intent intent = new Intent(getActivity(), EditFlashcardActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("deck", this.deck);
        bundle.putString("question", this.question);
        bundle.putString("answer", this.answer);
        bundle.putInt("id", this.id);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
