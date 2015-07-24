package practice.todolist;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by Oslo on 7/22/15.
 */
public class ToDoItems {

    // the table
    public static final String TODO_TABLE = "todo";
    // ids of columns
    public static final String COLUMN_ID = "_id";
    // the type of the to-do things
    public static final String COLUMN_TYPE = "type";
    // some notes of the to-do items and the details of the things
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_DETAILS = "details";

    public static final String DATABASE_CREATE = "create table " + TODO_TABLE
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TYPE + " text not null, "
            + COLUMN_NOTES + " text not null, "
            + COLUMN_DETAILS + " text not null);";


    public static void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        // set the logging message
        Log.w(ToDoItems.class.getName(), "Upgrading the table from version:" +
            oldVersion + ", to new version: " + newVersion);
        // delete the old version of the table and create a brand new one
        database.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        onCreate(database);
    }



}
