package practice.recyclerviewpractice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    // recyclerview objects to set layouts
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;

    private InfoAdapter infoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewLayouts();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewLayouts(){
        recyclerView = (RecyclerView)findViewById(R.id.displayList);
        recyclerView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        infoAdapter = new InfoAdapter(createList(15));
    }

    private List<Info> createList(int size) {
        List<Info> list = new ArrayList<Info>();
        for(int i = 0;i < size; i++ ){
            Info info = new Info();
            info.title = Info.INFO_PREFIX + i;
            info.name = Info.NAME_PREFIX + i;
            info.title = Info.TITLE_PREFIX + i;
            info.phone = Info.PHONE_PREFIX + i;
            info.email = Info.EMAIL_PREFIX + i;

            list.add(info);
        }
        return list;
    }

}
