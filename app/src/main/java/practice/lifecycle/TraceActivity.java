package practice.lifecycle;

import android.app.NotificationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class TraceActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        notice("onCreate");
    }


    @Override
    protected void onStop(){
        super.onStop();
        notice("onStop");
    }

    @Override
    protected void onResume(){
        super.onResume();
        notice("onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        notice("onPause");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        notice("onDestroy");
    }

    private void notice(String toNotice){
        //NotificationManager notificationManager = new NotificationManager();
        Toast.makeText(this, this.getClass().getName() + ", " + toNotice, Toast.LENGTH_LONG).show();
    }


}
