package practice.todolist;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import practice.todolist.ToDoItems;
import practice.todocontentprovider.ToDoContentProvider;


public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;
    private static final int DELETE_ID = Menu.FIRST + 1;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        this.getListView().setDividerHeight(2);
        fillData();
        registerForContextMenu(getListView());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.insert:
                createToDoList();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createToDoList(){
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }

    private void fillData(){
        String[] fromWhere = new String[]{ToDoItems.COLUMN_NOTES};
        int[] toWhere = new int[]{R.id.label};
        getLoaderManager().initLoader(0, null, this);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.todo, null, fromWhere, toWhere, 0);
        setListAdapter(simpleCursorAdapter);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterViewCompat.AdapterContextMenuInfo info = (AdapterViewCompat.AdapterContextMenuInfo) item
                        .getMenuInfo();
                Uri uri = Uri.parse(ToDoContentProvider.CONTENT_URI + "/"
                        + info.id);
                getContentResolver().delete(uri, null, null);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id){
        super.onListItemClick(listView, view, position, id);
        Intent intent = new Intent(this, ToDoActivity.class);
        Uri uri = Uri.parse(ToDoContentProvider.CONTENT_URI + "/" + id);
        intent.putExtra(ToDoContentProvider.CONTENT_ITEM_TYPE, uri);
        startActivity(intent);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        //CursorLoader cursorLoader = new CursorLoader(this, ToDoContentProvider.CONTENT_URI, pro)
        menu.add(0, DELETE_ID, 0, R.string.delete_item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {ToDoItems.COLUMN_ID, ToDoItems.COLUMN_NOTES};
        CursorLoader cursorLoader = new CursorLoader(this, ToDoContentProvider.CONTENT_URI, projection, null,null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        simpleCursorAdapter.swapCursor(null);
    }


}
