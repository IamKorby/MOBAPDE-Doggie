package com.example.angeloamadora.quiz2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by Angelo Amadora on 2/18/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    final static String SCHEMA = "notes";

    public DatabaseOpenHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create tables here
        // CREATE TABLE note = (id
        String sql = "CREATE TABLE " + Note.TABLE_NAME + " (" + Note.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Note.COLUMN_TITLE + " TEXT,"
                + Note.COLUMN_NOTE + " INTEGER);";

        db.execSQL(sql);

    }

    public long AddNote(Note n) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Note.COLUMN_TITLE, n.getTitle());
        cv.put(Note.COLUMN_NOTE, n.getNote());
        long id = db.insert(Note.TABLE_NAME, null, cv);
        db.close();
        return id;

    }

    public Note getNote(int id) {
        SQLiteDatabase db = getReadableDatabase();
        // SELECT * FROM NOTE WHERE id = ?

        Cursor c = db.query(Note.TABLE_NAME, null, " " + Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        Note n = new Note();
        if (c.moveToFirst()) {
            n.setId(c.getInt(c.getColumnIndex(Note.COLUMN_ID)));
            n.setTitle(c.getString(c.getColumnIndex(Note.COLUMN_TITLE)));
            n.setNote(c.getString(c.getColumnIndex(Note.COLUMN_NOTE)));


        } else {
            n = null;
        }

        db.close();
        c.close();
        return n;
    }

    public Cursor getNotes() {
//        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =  db.query(Note.TABLE_NAME,
                null, null, null, null, null, null);


//        if(c.moveToFirst()){
//            do {
//                Note n = new Note();
//                n.setId(c.getInt(c.getColumnIndex(Note.COLUMN_ID)));
//                n.setTitle(c.getString(c.getColumnIndex(Note.COLUMN_TITLE)));
//                n.setNote(c.getString(c.getColumnIndex(Note.COLUMN_NOTE)));
//
//                notes.add(n);
//            }while(c.moveToNext());
//        }

        //we dont close the dbc onnection or else adapter wont work
        return c;
    }

    public int updateNote(Note updatedNote){
        //updatedNote contains the ORIGINAL_id
        // updatedNote contains the NEW title and note
        // return the number of rows affected

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Note.COLUMN_TITLE,updatedNote.getTitle());
        cv.put(Note.COLUMN_NOTE, updatedNote.getNote());



        return db.update(Note.TABLE_NAME,
                cv,
                Note.COLUMN_ID+"=?",
                new String[]{String.valueOf(updatedNote.getId())});


    }

    public int deleteNote(int id){

        //id is the id of the note that we want to delete
        //return the number of rows affected
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(Note.TABLE_NAME,
                Note.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exists
        // call onCreate to create tables
    }
}
