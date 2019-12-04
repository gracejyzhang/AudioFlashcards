package com.example.audioflashcards.database;

import java.io.Serializable;

public class Flashcard implements Serializable {
    public static final String TABLE_NAME = "flashcards";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_DECK = "deck";

    private int id;
    private String question;
    private String answer;
    private String deck;
    private boolean multiselect = false;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_QUESTION + " TEXT," + COLUMN_ANSWER + " TEXT," + COLUMN_DECK + " TEXT" + ")";

    public Flashcard() {
    }

    public Flashcard(int id, String question, String answer, String deck) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.deck = deck;
    }

    public int getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getDeck() {
        return this.deck;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public void setMultiselect(boolean bool) { this.multiselect = bool; }

    public boolean getMultiselect() { return this.multiselect; }
}
