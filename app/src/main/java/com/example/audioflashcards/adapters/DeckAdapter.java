package com.example.audioflashcards.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import com.example.audioflashcards.R;
import com.example.audioflashcards.activities.FlashcardActivity;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.DeckViewHolder> {

    private String[] dataset;
    private Context context;

    public class DeckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Button deckButton;

        public DeckViewHolder(View v) {
            super(v);
            this.deckButton = (Button)v.findViewById(R.id.deck_button);
            this.deckButton.setOnClickListener(this);
        }

        public void onClick(View v) {
            onDeckClick(v, getAdapterPosition());
        }
    }

    public DeckAdapter(String[] dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    public DeckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_deck, parent, false);
        DeckViewHolder vh = new DeckViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(DeckViewHolder vh, int position) {
        vh.deckButton.setText(this.dataset[position]);
    }

    public int getItemCount() {
        return this.dataset.length;
    }

    public void onDeckClick(View v, int position) {
        String deck = getItem(position);

        Intent intent = new Intent(context, FlashcardActivity.class);
        Bundle b = new Bundle();
        b.putString("deck", deck);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    // convenience method for getting data at click position; unnecessary?
    private String getItem(int position) {
        return this.dataset[position];
    }
}
