package com.example.audioflashcards.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "flashcardsDB";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Flashcard.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Flashcard.TABLE_NAME);
        onCreate(db);
    }

    public long addFlashCard(String question, String answer, String deck) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Flashcard.COLUMN_QUESTION, question);
        values.put(Flashcard.COLUMN_ANSWER, answer);
        values.put(Flashcard.COLUMN_DECK, deck);

        long id = db.insert(Flashcard.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // open and close database (and close cursor) outside of method when calling
    public Cursor getFlashcards(String deck) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {Flashcard.COLUMN_ID, Flashcard.COLUMN_QUESTION, Flashcard.COLUMN_ANSWER};
        String selection = Flashcard.COLUMN_DECK + " = ?";
        String[] selectionArgs = { deck };

        String sortOrder = Flashcard.COLUMN_ID;

        Cursor cursor = db.query(Flashcard.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    public ArrayList<Flashcard> getFlashcardList(String deck) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Flashcard> flashcards = new ArrayList<Flashcard>();
        String[] projection = {Flashcard.COLUMN_ID, Flashcard.COLUMN_QUESTION, Flashcard.COLUMN_ANSWER};
        String selection = Flashcard.COLUMN_DECK + " = ?";
        String[] selectionArgs = { deck };
        String sortOrder = Flashcard.COLUMN_ID;
        Cursor cursor = db.query(Flashcard.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

        int indexID = cursor.getColumnIndex(Flashcard.COLUMN_ID);
        int indexQuestion = cursor.getColumnIndex(Flashcard.COLUMN_QUESTION);
        int indexAnswer = cursor.getColumnIndex(Flashcard.COLUMN_ANSWER);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(indexID);
            String question = cursor.getString(indexQuestion);
            String answer = cursor.getString(indexAnswer);

            flashcards.add(new Flashcard(id, question, answer, deck));
        }

        return flashcards;
    }

    public String[] getDecks() {
        SQLiteDatabase db = this.getReadableDatabase();

        // should selection be all/*?
        Cursor cursor = db.query(true, Flashcard.TABLE_NAME, new String[] {Flashcard.COLUMN_DECK}, null, null, Flashcard.COLUMN_DECK, null, null, null);
        String[] decks = new String[cursor.getCount()];
        int position = 0;

        if (cursor.moveToNext()) {
            do {
                decks[position] = cursor.getString(cursor.getColumnIndex(Flashcard.COLUMN_DECK));
                position++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return decks;
    }

    public long updateFlashcard(Flashcard card) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Flashcard.COLUMN_QUESTION, card.getQuestion());
        values.put(Flashcard.COLUMN_ANSWER, card.getAnswer());
        values.put(Flashcard.COLUMN_DECK, card.getDeck());

        long id = db.update(Flashcard.TABLE_NAME, values, Flashcard.COLUMN_ID + " = ?", new String[] { String.valueOf(card.getId())});
        db.close();
        return id;
    }

    public void deleteFlashcard(Flashcard card) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Flashcard.TABLE_NAME, Flashcard.COLUMN_ID + " = ?", new String[] { String.valueOf(card.getId())});
        db.close();
    }
}
