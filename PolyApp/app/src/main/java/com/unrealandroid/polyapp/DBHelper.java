package com.unrealandroid.polyapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.unrealandroid.polyapp.event.Event;
import com.unrealandroid.polyapp.projet_news.Project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    protected static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/";
    protected static String DB_NAME = "dbapps.db";

    protected SQLiteDatabase myDataBase;
    private final Context myContext;

    public DBHelper(Context context) throws SQLException, IOException {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
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

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public  SQLiteDatabase getMyDataBase(){
        return myDataBase;
    }

    public String testNews(){
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM News", null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("title"));
    }

    public String testProject(){
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM Project", null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("title"));
    }

    public String testEvent(){
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM Event", null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("title"));
    }


    public Event getEvent(Cursor cursor){
        cursor.moveToFirst();
        return new Event(
                cursor.getInt(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("content")),
                cursor.getInt(cursor.getColumnIndex("location-lat")),
                cursor.getInt(cursor.getColumnIndex("location-long")));
    }

    public ArrayList<Event> getAllEvent(){
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM Event", null);
        ArrayList<Event> listArticle = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            listArticle.add(new Event(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getInt(cursor.getColumnIndex("location-lat")),
                    cursor.getInt(cursor.getColumnIndex("location-long"))));
            cursor.moveToNext();
        }
        cursor.close();
        return listArticle;
    }

    public ArrayList<Project> getAllProject(){
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM Project", null);
        ArrayList<Project> listProject = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            listProject.add(new Project(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("imagePath")),
                    cursor.getString(cursor.getColumnIndex("title"))));
            cursor.moveToNext();
        }
        cursor.close();
        return listProject;
    }

    public Cursor getEventCursor(){
        Cursor cursor =  myDataBase.rawQuery("SELECT * FROM Event", null);
        cursor.moveToFirst();
        return cursor;
    }

    public Article getArticle(String id) {

        Cursor cu = myDataBase.rawQuery("SELECT * FROM News WHERE _id = " + id , null);
        cu.moveToFirst();

        //TODO : Get also the date

        Article toReturn  = new Article(
            cu.getString(cu.getColumnIndex("title")),
                cu.getString(cu.getColumnIndex("content")),
                cu.getString(cu.getColumnIndex("imagePath")),
                cu.getString(cu.getColumnIndex("date"))

        );

        return toReturn;
    }
}