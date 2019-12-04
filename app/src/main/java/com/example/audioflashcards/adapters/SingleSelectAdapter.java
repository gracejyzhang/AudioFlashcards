package com.example.audioflashcards.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.audioflashcards.R;
import com.example.audioflashcards.activities.EditFlashcardActivity;
import com.example.audioflashcards.database.Flashcard;

import java.util.ArrayList;

public class SingleSelectAdapter extends RecyclerView.Adapter<SingleSelectAdapter.SingleSelectViewHolder> {
    private Context context;
    private ArrayList<Flashcard> flashcards;

    public SingleSelectAdapter(Context context, ArrayList<Flashcard> flashcards) {
        this.context = context;
        this.flashcards = flashcards;
    }

    public void setFlashcards(ArrayList<Flashcard> flashcards) {
        this.flashcards = new ArrayList<>();
        this.flashcards = flashcards;
        notifyDataSetChanged();
    }

    @Override
    public SingleSelectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.recycler_single_select, viewGroup, false);
        return new SingleSelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleSelectViewHolder vh, int position) {
        vh.bind(this.flashcards.get(position));
    }

    @Override
    public int getItemCount() {
        return this.flashcards.size();
    }

    class SingleSelectViewHolder extends RecyclerView.ViewHolder {

        private TextView recyclerQuestion;
        private TextView recyclerAnswer;

        SingleSelectViewHolder( View v) {
            super(v);
            this.recyclerQuestion = (TextView) v.findViewById(R.id.ss_question);
            this.recyclerAnswer = (TextView) v.findViewById(R.id.ss_answer);
        }

        void bind(final Flashcard flashcard) {
            this.recyclerQuestion.setText(flashcard.getQuestion());
            this.recyclerAnswer.setText(flashcard.getAnswer());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEditClick(flashcard);
                }
            });
        }

        public void onEditClick(Flashcard card) {
            Intent intent = new Intent(context, EditFlashcardActivity.class);

            Bundle b = new Bundle();
            b.putString("deck", card.getDeck());
            b.putString("question", card.getQuestion());
            b.putString("answer", card.getAnswer());
            b.putInt("id", card.getId());
            intent.putExtras(b);

            context.startActivity(intent);
        }
    }
}

