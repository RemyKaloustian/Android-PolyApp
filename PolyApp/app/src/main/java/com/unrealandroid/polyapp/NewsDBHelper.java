package com.unrealandroid.polyapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/";
    private static String DB_NAME = "unrealAndroidApp.db";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public NewsDBHelper(Context context) throws SQLException, IOException {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        boolean dbexist = checkDataBase();
        /*if (dbexist) {
            System.out.println("Database exists");
            openDataBase();
        } else {
            System.out.println("Database doesn't exist");
            createDataBase();
        }*/
        //readBase();
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(!dbExist){
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e){
            //database doesn't exist yet.
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public String test(){
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM News", null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("title"));
    }
    /*public ArrayList<Article> readBase(){
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM news ORDER BY date DESC", null);
        ArrayList<Article> listArticle = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            listArticle.add(new Article(cursor.getInt(cursor.getColumnIndex("_id")),
                                        cursor.getString(cursor.getColumnIndex("title")),
                                        cursor.getString(cursor.getColumnIndex("content")),
                                        cursor.getString(cursor.getColumnIndex("author")),
                                        cursor.getString(cursor.getColumnIndex("date")),
                                        cursor.getInt(cursor.getColumnIndex("category")),
                                        cursor.getInt(cursor.getColumnIndex("media_type")),
                                        cursor.getString(cursor.getColumnIndex("media_path"))));
            cursor.moveToNext();
        }
        cursor.close();
        return listArticle;
    }

    public Article getArticle(Cursor cursor){
        return new Article(cursor.getInt(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("content")),
                cursor.getString(cursor.getColumnIndex("author")),
                cursor.getString(cursor.getColumnIndex("date")),
                cursor.getInt(cursor.getColumnIndex("category")),
                cursor.getInt(cursor.getColumnIndex("media_type")),
                cursor.getString(cursor.getColumnIndex("media_path")));
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public  SQLiteDatabase getMyDataBase(){
        return myDataBase;
    }
}