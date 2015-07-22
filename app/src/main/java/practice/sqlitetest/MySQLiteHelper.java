package practice.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Oslo on 7/21/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CONTENTS = "contents";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTENT = "content";
    public static final String DATABASE_NAME = "mydata.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_CREATE = "create table " +
            TABLE_CONTENTS + "(" + COLUMN_CONTENT + " integer primary key autoincrement, " +
            COLUMN_CONTENT + " text not null);";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // onCreate method will create the database to
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "upgrading from old version:" + oldVersion +
            "to new version:" + newVersion + ", this will destroy all the old data...");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENTS);
        onCreate(db);
    }
}
