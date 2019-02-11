package com.example.kontess.booklibrary;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Kontess on 14.5.2018.
 */

public class Veritabani extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "KITAPLIK";
    private static Integer DATABASE_VERSION = 1;
    private static String TABLE_COLUMN_BOOKNAME = "Name";
    private static String TABLE_COLUMN_ID = "ID" ;
    private static String TABLE_COLUMN_SUBJECT = "Subject";
    private static String TABLE_COLUMN_AUTHOR = "Author";
    private static String TABLE_COLUMN_AUTHORDEATHYEAR = "DeathYear";

    private static String TABLE_NAME = "Kitaplar" ;


    public Veritabani(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String command = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " ( " +
                TABLE_COLUMN_BOOKNAME + " TEXT, "+
                TABLE_COLUMN_ID + " INTEGER, "+
                TABLE_COLUMN_AUTHOR + " TEXT, "+
                TABLE_COLUMN_SUBJECT + " TEXT, "+
                TABLE_COLUMN_AUTHORDEATHYEAR + " INTEGER "+ "); ";

        db.execSQL(command);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void ClearAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);
        db.close();

    }

    public void AddBookInfo(Book book){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("Name",book.getName());
        values.put("ID",book.getId());
        values.put("Author",book.getAuthor());
        values.put("Subject", book.getSubjects());
        values.put("DeathYear",book.getDeathyear());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }


    public ArrayList<Book> GetAllLike (String s)

    {
        ArrayList<Book> bulunanlar = new ArrayList<>();

        String araCommand = "SELECT * FROM "+ TABLE_NAME + " WHERE "+ TABLE_COLUMN_BOOKNAME + " LIKE " + " '%"+s+"%' " +
                " OR "+ TABLE_COLUMN_SUBJECT + " LIKE " +" '%"+s+"%' "+
                " OR "+ TABLE_COLUMN_AUTHOR + " LIKE " + " '%"+s+"%' ";

        // " OR "+ TABLE_COLUMN_SUBJECT + " LIKE " +" '%"+s+"%' "+

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(araCommand,null);
        cursor.moveToFirst();

        while (cursor.moveToNext())
        {
            Integer id = cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_BOOKNAME));
            String author = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_AUTHOR));
            Integer authordeath = cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_AUTHORDEATHYEAR));
            String subject = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_SUBJECT));


            Book book = new Book (name, author,authordeath, subject, id);

            bulunanlar.add(book);


        }



        cursor.close();
        db.close();

            return bulunanlar;

    }
}
