package practice.todocontentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

import practice.todolist.ToDoDBHelper;
import practice.todolist.ToDoItems;

/**
 * Created by Oslo on 7/22/15.
 */
public class ToDoContentProvider extends ContentProvider {

    // the database helper
    private ToDoDBHelper toDoDBHelper;
    // the integers for the use of UriMatchers
    private static final int TODOS = 10;
    private static final int TODO_ID = 20;

    private static final String BASE_PATH = "todos";
    private static final String AUTHORITY = "practice.todocontentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/todos";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/todo";

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, TODOS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TODO_ID);
    }


    @Override
    public boolean onCreate() {
        toDoDBHelper = new ToDoDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // use sqlitequerybuilder
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        // check if the columns are available
        checkColumns(projection);
        sqLiteQueryBuilder.setTables(ToDoItems.TODO_TABLE);

        // check if match the uri type
        int type = uriMatcher.match(uri);

        switch (type){
            case TODO_ID:
                // if it is id, add it to the query
                sqLiteQueryBuilder.appendWhere(ToDoItems.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }


        SQLiteDatabase database = toDoDBHelper.getWritableDatabase();
        Cursor cursor = sqLiteQueryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        // make the resolver is getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int type = uriMatcher.match(uri);
        SQLiteDatabase db = toDoDBHelper.getWritableDatabase();
        long id = 0;
        switch (type) {
            case TODOS:
                id = db.insert(ToDoItems.TODO_TABLE, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int type = uriMatcher.match(uri);
        SQLiteDatabase db = toDoDBHelper.getWritableDatabase();
        int deleted = 0;
        switch(type) {
            case TODO_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)){
                    deleted = db.delete(ToDoItems.TODO_TABLE, ToDoItems.COLUMN_ID + "=" + id, null);
                } else {
                    deleted = db.delete(ToDoItems.TODO_TABLE, ToDoItems.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            case TODOS:
                deleted = db.delete(ToDoItems.TODO_TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown deleten of URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int type = uriMatcher.match(uri);
        SQLiteDatabase db = toDoDBHelper.getWritableDatabase();
        int updated = 0;
        switch (type) {
            case TODOS:
                updated = db.update(ToDoItems.TODO_TABLE, values, selection, selectionArgs);
                break;
            case TODO_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)){
                    updated = db.update(ToDoItems.TODO_TABLE, values, ToDoItems.COLUMN_ID + "=" + id, null);
                } else {
                    updated = db.update(ToDoItems.TODO_TABLE, values, ToDoItems.COLUMN_ID + "=" + id +
                        " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri Updating: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updated;
    }

    private void checkColumns(String[] projection){
        String[] check = {ToDoItems.COLUMN_TYPE, ToDoItems.COLUMN_NOTES, ToDoItems.COLUMN_DETAILS, ToDoItems.COLUMN_ID};
        if(projection != null) {
            HashSet<String> request = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> toCheck = new HashSet<String>(Arrays.asList(check));

            if (!toCheck.containsAll(request)){
                throw new IllegalArgumentException("Unknown columns....");
            }
        }

    }


}
