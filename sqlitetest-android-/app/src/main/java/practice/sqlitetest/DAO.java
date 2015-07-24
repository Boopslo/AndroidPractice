package practice.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Oslo on 7/21/15.
 */
public class DAO {

    /*
        Data Access Object class:
            you need the SQLiteDatabase class to access the database;
            the sqlitedatabasehelper class helps initialize and upgrade the database;
            you also need to write the object that is used to set and get all the elements in the database

        the class has to include methods to open and close databases
        so does methods to cursor, create, delete, return the elements
     */

    private SQLiteDatabase database;
    private MySQLiteHelper sqLiteHelper;
    private String[] columns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_CONTENT};

    public DAO(Context context) {
        sqLiteHelper = new MySQLiteHelper(context);
    }

    // the open method initializes the data by call writable method of sqlitehelper
    public void openDB() throws SQLException {
        database = sqLiteHelper.getWritableDatabase();
    }

    public void closeDB(){
        sqLiteHelper.close();
    }

    public Content createContent(String content){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CONTENT, content);

        long insertId = database.insert(MySQLiteHelper.TABLE_CONTENTS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_CONTENTS, columns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Content newContent = goToContent(cursor);
        cursor.close();
        return newContent;

    }

    public Content goToContent(Cursor cursor){
        Content content = new Content();
        content.setId(cursor.getLong(0));
        content.setContent(cursor.getString(1));
        return content;
    }

    // log the warning message and delete the content of the id
    public void deleteContent(Content content){
        long id = content.getId();
        Log.w("Content" + id, " has been deleted");
        database.delete(MySQLiteHelper.TABLE_CONTENTS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Content> getAllContents() {
        List<Content> contents = new ArrayList<Content>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_CONTENTS, columns, null, null, null, null, null);
        if( cursor.moveToFirst() ){
            while(!cursor.isAfterLast()){
                Content toAdd = goToContent(cursor);
                contents.add(toAdd);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return contents;

    }


}
