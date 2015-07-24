package practice.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oslo on 7/22/15.
 */
public class ToDoDBHelper extends SQLiteOpenHelper {

    //public static final String TABLE_CON
    public static final String DATABASE_NAME = "todotable.db";
    public static final int DATABASE_VERSION = 1;

    // must implement the constructor of the database helper
    // return the database_name and the version
    public ToDoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // must overwrite the database onCreate method, just call the database's onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {
        ToDoItems.onCreate(db);
    }

    // onUpdrade method , also just call the Database's upgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ToDoItems.onUpgrade(db, oldVersion, newVersion);
    }


}
