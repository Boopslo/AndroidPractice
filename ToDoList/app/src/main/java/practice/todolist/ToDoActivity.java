package practice.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import practice.todolist.ToDoItems;
import practice.todocontentprovider.ToDoContentProvider;


public class ToDoActivity extends Activity {

    private Spinner type;
    private EditText titleText;
    private EditText bodyText;
    private Button button;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_edit);

        initializing();
        Bundle extras = getIntent().getExtras();
        uri = (savedInstanceState == null) ? null : (Uri)savedInstanceState.getParcelable(ToDoContentProvider.CONTENT_ITEM_TYPE);

        if(extras != null){
            uri = extras.getParcelable(ToDoContentProvider.CONTENT_ITEM_TYPE);
            fillData(uri);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(titleText.getText().toString())){
                    toastMessage();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void initializing(){
        type = (Spinner)findViewById(R.id.type);
        titleText = (EditText)findViewById(R.id.edit_notes);
        bodyText = (EditText)findViewById(R.id.edit_details);
        button = (Button)findViewById(R.id.confirm_button);

    }

    private void fillData(Uri uri){
        String[] projection = { ToDoItems.COLUMN_NOTES, ToDoItems.COLUMN_DETAILS, ToDoItems.COLUMN_TYPE};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            String todoType = cursor.getString(cursor.getColumnIndexOrThrow(ToDoItems.COLUMN_TYPE));
            for (int i = 0; i < type.getCount(); i++){
                String s = (String) type.getItemAtPosition(i);
                if(s.equalsIgnoreCase(todoType)){
                    type.setSelection(i);
                }
            }

            titleText.setText(cursor.getString(cursor.getColumnIndexOrThrow(ToDoItems.COLUMN_NOTES)));
            bodyText.setText(cursor.getString(cursor.getColumnIndexOrThrow(ToDoItems.COLUMN_DETAILS)));
            cursor.close();
        }
    }

    private void toastMessage(){
        Toast.makeText(ToDoActivity.this, "Please give some notes", Toast.LENGTH_LONG).show();
    }

    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        saveState();
        bundle.putParcelable(ToDoContentProvider.CONTENT_ITEM_TYPE, this.uri);
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveState();
    }

    private void saveState(){
        String todo_type = (String)type.getSelectedItem();
        String todo_notes = titleText.getText().toString();
        String todo_details = bodyText.getText().toString();
        // check the value if available
        if( (todo_notes.length() == 0) && (todo_details.length() == 0) ){
            return;
        }

        ContentValues values = new ContentValues();
        values.put(ToDoItems.COLUMN_TYPE, todo_type);
        values.put(ToDoItems.COLUMN_NOTES, todo_notes);
        values.put(ToDoItems.COLUMN_DETAILS, todo_details);

        if(this.uri == null)
        {
            this.uri = getContentResolver().insert(ToDoContentProvider.CONTENT_URI, values);
        } else {
            getContentResolver().update(this.uri, values, null, null);
        }

    }


}
