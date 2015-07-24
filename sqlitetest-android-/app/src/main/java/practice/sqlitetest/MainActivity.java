package practice.sqlitetest;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.*;
import android.view.View;
import android.widget.ArrayAdapter;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;


public class MainActivity extends ListActivity {

    DAO contentDAO;
    List<Content> contentList;
    ArrayAdapter<Content> contentArrayAdapter, getAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();

    }

    private void setUp(){
        contentDAO = new DAO(this);
        try {
            contentDAO.openDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        contentList = contentDAO.getAllContents();
        contentArrayAdapter = new ArrayAdapter<Content>(this,
                android.R.layout.simple_list_item_1, contentList);
        setListAdapter(contentArrayAdapter);

    }

    public void onClick(View view){
        getAdapter = (ArrayAdapter<Content>)getListAdapter();
        Content content = null;
        switch(view.getId()){
            case R.id.add_button:
                String[] array = new String[]{"Heellloooo", "woooooooow", "babooo", "shampooo", "doooodo", "wheyueo~"};
                int simulate = new Random().nextInt(6);
                content = contentDAO.createContent(array[simulate]);
                getAdapter.add(content);
                break;
            case R.id.delete_button:
                if(getListAdapter().getCount() > 0){
                    content = (Content)getListAdapter().getItem(0);
                    contentDAO.deleteContent(content);
                    getAdapter.remove(content);
                }
                break;
            default:
                break;
        }
        getAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        try {
            contentDAO.openDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause(){
        contentDAO.closeDB();
        super.onPause();
    }


}
