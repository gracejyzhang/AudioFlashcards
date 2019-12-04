package com.example.audioflashcards.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.audioflashcards.R;
import com.example.audioflashcards.activities.AddFlashcardActivity;
import com.example.audioflashcards.activities.SelectRecyclerActivity;
import com.example.audioflashcards.activities.SingleSelectActivity;

public class DeckTitlepageFragment extends Fragment {

    private String deck;
    private Button addButton, editButton, deleteButton;
    Bundle bundle = new Bundle();

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        this.deck = b.getString("deck");

        this.bundle.putString("deck", this.deck);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View deckTitleLayout = inflater.inflate(R.layout.fragment_deck_titlepage, container, false);

        TextView deckTitle = (TextView)deckTitleLayout.findViewById(R.id.deck_frontpage);
        deckTitle.setText(this.deck);
        this.addButton = (Button)deckTitleLayout.findViewById(R.id.add_button);
        this.editButton = (Button)deckTitleLayout.findViewById(R.id.edit_button);
        this.deleteButton = (Button)deckTitleLayout.findViewById(R.id.delete_button);

        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFlashcardButton();
            }
        });
        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFlashcardButton();
            }
        });
        this.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFlashcardButton();
            }
        });

        return deckTitleLayout;
    }

    public void addFlashcardButton() {
        Intent intent = new Intent(getActivity(), AddFlashcardActivity.class);
        intent.putExtras(this.bundle);
        startActivity(intent);
    }

    public void deleteFlashcardButton() {
        Intent intent = new Intent(getActivity(), SelectRecyclerActivity.class);
        intent.putExtras(this.bundle);
        startActivity(intent);
    }

    public void editFlashcardButton() {
        Intent intent = new Intent(getActivity(), SingleSelectActivity.class);
        intent.putExtras(this.bundle);
        startActivity(intent);
    }
}
