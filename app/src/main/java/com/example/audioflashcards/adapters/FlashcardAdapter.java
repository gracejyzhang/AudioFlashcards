package com.example.audioflashcards.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.audioflashcards.R;
import com.example.audioflashcards.database.Flashcard;
import java.util.ArrayList;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    private Context context;
    private ArrayList<Flashcard> flashcards;

    public FlashcardAdapter(Context context, ArrayList<Flashcard> flashcards) {
        this.context = context;
        this.flashcards = flashcards;
    }

    public FlashcardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_flashcards, viewGroup, false);
        return new FlashcardViewHolder(view);
    }

    public void onBindViewHolder(FlashcardViewHolder vh, int position) {
        vh.bind(flashcards.get(position));
        vh.recyclerQuestion.setText(this.flashcards.get(position).getQuestion());
        vh.recyclerAnswer.setText(this.flashcards.get(position).getAnswer());
    }

    public int getItemCount() {
        return this.flashcards.size();
    }

    public void setFlashcards(ArrayList<Flashcard> flashcards) {
        this.flashcards = new ArrayList<>();
        this.flashcards = flashcards;
        notifyDataSetChanged();
    }

    public ArrayList<Flashcard> getAll() {
        return this.flashcards;
    }

    public ArrayList<Flashcard> getSelected() {
        ArrayList<Flashcard> selected = new ArrayList<>();
        for (int i = 0; i < flashcards.size(); i++) {
            if (flashcards.get(i).getMultiselect()) {
                selected.add(flashcards.get(i));
            }
        }
        return selected;
    }

    public class FlashcardViewHolder extends RecyclerView.ViewHolder{

        private TextView recyclerQuestion;
        private TextView recyclerAnswer;
        private CheckBox checkbox;

        public FlashcardViewHolder(View v) {
            super(v);
            this.recyclerQuestion = (TextView) v.findViewById(R.id.recycler_question);
            this.recyclerAnswer = (TextView) v.findViewById(R.id.recycler_answer);
            this.checkbox = (CheckBox) v.findViewById(R.id.checkBox);
        }

        void bind(final Flashcard flashcard) {
            this.recyclerQuestion.setText(flashcard.getQuestion());
            this.recyclerAnswer.setText(flashcard.getAnswer());
            this.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flashcard.setMultiselect(!flashcard.getMultiselect());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flashcard.setMultiselect(!flashcard.getMultiselect());
                    checkbox.setChecked(flashcard.getMultiselect());
                }
            });
        }
    }
}
